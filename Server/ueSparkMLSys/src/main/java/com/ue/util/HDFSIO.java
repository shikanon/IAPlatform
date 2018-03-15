/**
 * Copyright 2017 Institute of Computing Technology, Chinese Academy of Sciences.
 * Licensed under the terms of the Apache 2.0 license.
 * Please see LICENSE file in the project root for terms
 */
package com.ue.util;


import com.google.common.collect.Lists;
import org.apache.commons.fileupload.FileItem;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.io.IOUtils;

import java.io.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static java.util.Arrays.asList;

/**
 * Utility functions for HDFS
 */
public class HDFSIO {
    private static Logger logger = Logger.getLogger(HDFSIO.class.getName());
    private static Configuration conf = new Configuration();
    private static FileSystem fs;
    private static final int LIMIT_LINE = 1000000;

    static {
        conf.set("fs.default.name", Constants.NAME_NODE);

        //Wheather client use hostname to visit datanode or not
        conf.set("dfs.client.use.datanode.hostname", Constants.HOSTNAME_CON);
        try {
            fs = FileSystem.get(conf);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Write a file in hdfs
     *
     * @param uri     target file uri
     * @param content file content
     * @throws IOException
     */
    public static void upload(String uri, String content) throws IOException {
        Path path = new Path(Constants.NAME_NODE + "/" + uri);
        if (fs.exists(path))
            fs.delete(path, true);
        OutputStream out = fs.create(path);
        InputStream rf = new ByteArrayInputStream(content.getBytes());


        IOUtils.copyBytes(rf, out, 4096, true);
        out.close();
    }

    /**
     * Create a new file on HDFS and add content,
     * it should be noted that the file did not exist
     *
     * @param uri  HDFS uri
     * @param item InputStream item to uploadPrograrm
     * @throws IOException
     */
    public static void uploadModel(String uri, InputStream item)
            throws IOException {
        FSDataOutputStream out =
                fs.create(new Path(Constants.NAME_NODE + "/" + uri));
        // IOUtils.copyBytes method
        IOUtils.copyBytes(item, out, 4096, false);
        out.close();
    }

    /**
     * Upload file to HDFS
     *
     * @param uri  target file path
     * @param item file to uploadPrograrm
     * @param name name of the new file
     * @throws IOException
     */
    public static void uploadfile(String uri, InputStream item, String name)
            throws IOException {
        System.out.println("[dstPath]" + Constants.NAME_NODE + "/" + uri + name);
        FSDataOutputStream out = fs.create(new Path(Constants.NAME_NODE + uri
                + name));
        IOUtils.copyBytes(item, out, 4096, true);
    }

    /**
     * @auther: 作者 bk
     * @description: 类说明 上传文件至hdfs
     * @param: 参数说明 scp:本地文件绝对路径，hdfsLo：hdfs存放路径，deletion：上传后是否删除本地文件
     * @result 结果说明 返回true或者false
     * @exceptions 异常说明
     */
    public static boolean uploadFile2Hdfs(String scpfile, String hdfsLo, boolean deletion) {
        boolean result = false;
        Path local = new Path(scpfile);
        Path hdfsdir = new Path(hdfsLo);
        try {
            fs = FileSystem.get(URI.create(Constants.NAME_NODE), conf, "hdfs");
            fs.copyFromLocalFile(deletion, local, hdfsdir);
            return true;
        } catch (IOException | InterruptedException e) {
            logger.info(e.getMessage());
        } finally {
            closeDFS();
        }
        return result;
    }

    /**
     * @auther: 作者 bk
     * @description: 类说明 上传文件至hdfs
     * @param: 参数说明 uuid：生成的uuid随机数目录，FileInputStream：hdfs写入流，name：文件名
     * @result 结果说明
     * @exceptions 异常说明 抛出异常
     */
    public static void uploadhdfs(String uuid, FileInputStream in, String name)
            throws IOException {
        System.out.println("[dstPath]" + Constants.NAME_NODE + "/" + uuid + "/" + name);
        String uri = Constants.DATASET_PATH + "/" + uuid + "/" + name;
        if (exist(uri) == true) {
            delete(uri);
        } else {
            FSDataOutputStream out = fs.create(new Path(Constants.DATASET_PATH + "/" + uuid
                    + "/" + name));
            IOUtils.copyBytes(in, out, 4096, true);
        }
    }

    public static void closeDFS() {
        try {
            if (fs != null) {
                fs.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete file in HDFS
     *
     * @param filePath target file path
     * @throws IOException
     */
    public static void delete(String filePath) throws IOException {
        String url = Constants.NAME_NODE + "/" + filePath;
        Path path = new Path(url);
        if (exist(url)) {
            boolean ok = fs.delete(path, true);
            logger.info("delete path:" + path + " " + (ok ? "successed" : "failed"));
        }
    }

    /**
     * Cat method
     *
     * @param uri
     * @return inputstream of cat file
     * @throws Exception
     */
    public static InputStream getInputStream(String uri) throws IOException {
        Path path = new Path(Constants.NAME_NODE + "/" + uri);
        if (!fs.exists(path))
            return null;
        if (fs.isDirectory(path)) {
            return searchFile(path);
        } else {
            InputStream in = fs.open(new Path(uri));
            return in;
        }
    }

    /**
     * Download file in HDFS
     *
     * @param uri of file to download
     * @return inputstream of the file to download
     * @throws Exception
     */
    public static InputStream downInputStream(String uri) throws IOException {
        Path path = new Path(uri);
        if (!fs.exists(path))
            return null;
        if (fs.isDirectory(path)) {
            return searchFile(path);
        } else {
            InputStream in = fs.open(new Path(uri));
            return in;
        }
    }

    /**
     * Search File in HDFS
     *
     * @param path search path
     * @return input stream
     * @throws IOException
     */
    private static InputStream searchFile(Path path) throws IOException {
        FileStatus[] status = fs.listStatus(path);
        InputStream in = null;
        if (status == null || status.length == 0)
            return null;

        path = status[0].getPath();
        for (FileStatus s : status) {
            if (!fs.isDirectory(s.getPath())) {
                if (!s.getPath().getName().equals("_SUCCESS")) {
                    in = fs.open(s.getPath());
                    return in;
                }
            } else {
                in = searchFile(s.getPath());
                if (in != null)
                    return in;
            }

        }

        return null;
    }

    /**
     * The same method as hdfs dfs -cat
     *
     * @param uri target file uri
     * @return content string in file
     * @throws IOException
     */
    public static String cat(String uri) throws IOException {
        InputStream in = getInputStream(uri);
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        IOUtils.copyBytes(in, out, 4096, true);
        return out.toString();
    }

    /**
     * Return the paths of files under directory uri  不包括目录
     */
    public static Path[] list(String uri) throws IOException {

        Path path = new Path(uri);
        if (!fs.exists(path))
            return null;
        FileStatus[] status = fs.listStatus(new Path(uri));

        List<FileStatus> statusesArray = new ArrayList<FileStatus>();


        for (FileStatus fs:status){

            if (!fs.isDirectory())
              statusesArray.add(fs);
        }


        int size=statusesArray.size();
        FileStatus[] newstatus=new FileStatus[size]; //去除目录后，新的文件Path

        for (int i=0;i<size;i++)
        {
            FileStatus f=statusesArray.get(i);
            newstatus[i]=f;
        }



        Path[] listedPaths = FileUtil.stat2Paths(newstatus);
        return listedPaths;
    }

    /**
     * Make directory in the uri position
     *
     * @param uri target position
     * @return whether success or not
     * @throws IOException
     */
    public static boolean mkdirs(String uri) throws IOException {
        Path path = new Path(Constants.NAME_NODE + "/" + uri);
        System.out.println("[mkdirs]" + path.toString());

        FsPermission dirPerm = new FsPermission(FsAction.ALL, FsAction.ALL, FsAction.ALL);
        Boolean flag = fs.mkdirs(path);
        if (flag)
            fs.setPermission(path, new FsPermission(dirPerm));
        return flag;
    }

    /**
     * Whether a file is exit or not
     *
     * @param uri target file uri
     * @return true/false
     * @throws IOException
     */
    public static boolean exist(String uri) throws IOException {
        return fs.exists(new Path(uri));
    }

    /**
     * Copy method
     *
     * @param src_uri Source file uri
     * @param dst_uri destination uri
     * @throws Exception
     */
    public static void copy(String src_uri, String dst_uri) throws Exception {
        FileUtil.copy(fs, new Path(src_uri), fs, new Path(dst_uri), false, conf);
    }

    /**
     * Rename a file in HDFS
     *
     * @param src target source file
     * @param dst destination file
     * @throws IOException
     */
    public static void rename(String src, String dst) throws IOException {
        fs.rename(new Path(Constants.NAME_NODE + "/" + src),
                new Path(Constants.NAME_NODE + "/" + dst));
    }

    /**
     * The same method as hdfs dfs -head
     *
     * @param uri target file url in HDFS
     * @param n   the position before you need
     * @return
     * @throws IOException
     */
    public static String head(String uri, int n) throws IOException {
        Path[] paths = HDFSIO.list(uri);
        StringBuffer sb = new StringBuffer();
        int line = 0;
        String lineStr = null;
        for (Path temp : paths) {
            InputStream in = HDFSIO.downInputStream(temp.toString());
            if (in == null)
                continue;
            BufferedReader br = new BufferedReader(new InputStreamReader(in));

            while (line++ < n && (lineStr = br.readLine()) != null) {
                if (lineStr.length() > 1024) lineStr = lineStr.substring(0, 1024) + "(...)";
                sb.append(lineStr);
                sb.append('\n');
            }
            br.close();
        }
        return sb.toString();
    }

    /**
     * To determine whether the file directory
     *
     * @param path target file path
     * @return
     * @throws IOException
     */
    public static boolean isDirectory(Path path) throws IOException {
        return fs.isDirectory(path);
    }

    /**
     * Read the file or directory size of a file under HDFS
     *
     * @param uri target File or directory path
     * @return The total size of all file sizes in the file size or directory (in KB)
     * @throws IOException
     */
    public static double getFileSize(String uri) throws IOException {
        Path path = new Path(uri);
        long totalSize = 0;
        if (HDFSIO.isDirectory(path)) {
            Path[] dirFilePath = HDFSIO.list(uri);
            for (Path p : dirFilePath) {
                totalSize += getFileSize(uri + "/" + p.getName()) * 1000.0;
            }
        } else {
            FileStatus status = fs.getFileStatus(path);
            totalSize = status.getLen();
        }

        return totalSize / 1000.0;
    }

    /**
     * Read total data on hdfs  by line
     *
     * @param uri   data path
     * @param limit if limit line
     * @return
     * @throws IOException
     */
    public static List<String> readAllData(String uri, Boolean limit) throws IOException {
        Path[] paths = HDFSIO.list(uri);
        String lineStr = null;
        List<String> result = new ArrayList<String>();
        int lineNum = 0;
        if (limit) {
            boolean overFlag = false;
            for (Path temp : paths) {
                InputStream in = HDFSIO.downInputStream(temp.toString());
                if (in == null)
                    continue;
                BufferedReader br = new BufferedReader(new InputStreamReader(in, "UTF-8"));

                while ((lineStr = br.readLine()) != null) {
                    lineNum = lineNum + 1;
                    result.add(lineStr);
                    if (lineNum > LIMIT_LINE) //If more than limit line number ,only display the limit line number
                    {
                        overFlag = true;
                        break;
                    }
                }
                if (overFlag)
                    break;
                br.close();
            }
        } else {
            for (Path temp : paths) {
                InputStream in = HDFSIO.downInputStream(temp.toString());
                if (in == null)
                    continue;
                BufferedReader br = new BufferedReader(new InputStreamReader(in));
                while ((lineStr = br.readLine()) != null) {
                    lineNum = lineNum + 1;
                    result.add(lineStr);
                }
                br.close();
            }
        }
        return result;
    }

    /**
     * Read hdfs page data
     *
     * @param uri   data path
     * @param start start position
     * @param count data count
     * @return
     * @throws IOException
     */
    public static List<String> readDataForPage(String uri, int start, int count) throws IOException {
        Path[] paths = HDFSIO.list(uri);
        int index = -1;
        String lineStr = null;
        List<String> result = new ArrayList<String>();
        for (Path temp : paths) {
            InputStream in = HDFSIO.downInputStream(temp.toString());
            if (in == null)
                continue;
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            while ((lineStr = br.readLine()) != null) {
                index = index + 1;
                if (index < start)
                    continue;
                else if (index >= start + count)
                    break;
                else
                    result.add(lineStr);
            }
            br.close();
        }
        logger.info("Read hdfs data page, start = " + start + ";count=" + count + ";result size = " + result.size());
        return result;
    }


}
