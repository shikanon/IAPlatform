<template>
  <div class="photoLabelExplain">
    <article class="markdown-body">
      <h1 id="_1"><a name="user-content-_1" href="#_1" class="headeranchor-link" aria-hidden="true"><span class="headeranchor"></span></a>图像标签化说明文档</h1>
    <hr />
    <blockquote>
      <p>图像标签化通过深度学习实现对图像内容自动描述功能，涉及到机器视觉、自然语言处理等模块。图像标签化不仅描述图像内有哪些物体，还描述物体之间的联系。</p>
    </blockquote>
    <h2 id="_2"><a name="user-content-_2" href="#_2" class="headeranchor-link" aria-hidden="true"><span class="headeranchor"></span></a>接口说明</h2>
    <h2 id="img2txt"><a name="user-content-img2txt" href="#img2txt" class="headeranchor-link" aria-hidden="true"><span class="headeranchor"></span></a>img2txt原理</h2>
    <h3 id="_3"><a name="user-content-_3" href="#_3" class="headeranchor-link" aria-hidden="true"><span class="headeranchor"></span></a>架构</h3>
    <p>图像标签化使用了encoder–decoder神经网络框架，首先，通过“编码”，将图片表示为固定长度的向量，然后再把向量进行“解码”为述自然语言描述。</p>
    <p>图像编码部分是将输入的图像序列表示为带有语意的向量，在这里我们使用基于 <em>GoogleLeNet</em> 的<a href="https://arxiv.org/abs/1512.00567"><em>Inception V3</em> </a>模型。</p>
    <p><em>GoogleLeNet</em> 在传统的卷积层和池化层后面引入了Inception结构，对网络中的传统卷积层进行修改，用于增加网络深度和宽度，提高深度升级网络性能。</p>
    <p><em>Inception V3</em>模型在卷积分解上进行了优化，引入 <em>Factorization into small convolutions</em> 思想，如图所示把$n \times n$维卷积分解为$1 \times n$,$n \times 1$为卷积。</p>
    <p><img alt="卷积分解" src="../assets/img/fig1.png" /></p>
    <p>同时，<em>Inception V3</em>模型优化了 <em>Inception Module</em> 的结构,有35 $\times$ 35, 17 $\times$ 17, 8 $\times$ 8三种不同结构，应用于 <em>Inception Module</em> 网络后部，此外，除了在 <em>Inception Module</em> 中使用分支，还在分支中使用分支，如图所示。</p>
    <p><img alt="模型优化" src="../assets/img/fig2.png" /></p>
    <p>这种分解极大地节省计算量，进一步增加了网络的深度和宽度，提高一层非线性扩展模型表达能力。随着$n$的增大，计算成本节省量增加。</p>
    <p>解码部分是一个长短时记忆网络模型（<em>Long Short-Term Memory Networks,LSTMs</em>）。这种网络结构在对长序列依赖问题中非常有效，通常用于机器翻译等序列建模任务。在这里，LSTMs为基于图像编码的语言模型。LSTMs的网络结构如图所示。<br />
      得到图像标签化结果：</p>
    <p><img alt="LSTMs结构图" src="../assets/img/lstm.png" /></p>
    <p>训练标签中的单词用嵌入模型(<em>embedding model</em>)表示，词汇中的每个单词都与固定常量的矢量相关联。</p>
    <p>在架构中，首先，通过 <em>Inception V3</em> 模型对图像进行预训练，把图像转变为单词嵌入向量空间。齐次，通过嵌入模型对训练标签中的单词与对应嵌入向量进行关联，最后，把 <em>Inception V3</em> 在内的所有的嵌入向量进行训练。</p>
    <p>图像标签化架构如图所示。</p>
    <p><img alt="图像标签化架构图" src="../assets/img/show_and_tell_architecture.png" /></p>
    <p>其中，$\left[ s_0, s_1,&hellip;,s_{N-1} \right] $为标签里的单词，$[w_es_0,w_es_1,&hellip;,w_es_{N-1}]$为对应的嵌入向量，$[p_1,p_2,&hellip;p_N]$<br />
      为句子中下一个单词的概率分布，$[\log {p_1(s_1)},&hellip;,\log{p_N(s_N)}]$为每步中正确单词的对数似然估计。</p>
    <h2 id="_4"><a name="user-content-_4" href="#_4" class="headeranchor-link" aria-hidden="true"><span class="headeranchor"></span></a>演示</h2>
    <p>上传图片：<br />
      <img alt="演示1" src="../assets/img/test1.jpg" /></p>
    <p>得到图像标签化结果：<br />
    <div class="codehilite"><pre>a man riding a wave on top of a surfboard .(p=0.0411787608649671)
a person riding a surf board on a wave .(p=0.015617915613974466)
a man riding a wave on a surfboard .p=(0.007279880837738181)
</pre></div>
    </p>
    <p>上传图片：<br />
      <img alt="演示2-" src="../assets/img/test4.jpg" /></p>
    <p>得到图像标签化结果：<br />
    <div class="codehilite"><pre>a group of young children standing next to each other .(p=0.0004594961514835304
a group of people standing next to each other holding umbrellas .(p=0.0003899570195326084
a group of young children standing next to each other holding umbrellas .(P=0.00020428822081726113
</pre></div>
    </p>
    </article>

  </div>
</template>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/mathjax/2.7.1/MathJax.js?config=TeX-AMS-MML_HTMLorMML"></script>

<script>
  MathJax.Hub.Config({
    config: ["MMLorHTML.js"],
    extensions: ["tex2jax.js"],
    jax: ["input/TeX"],
    tex2jax: {
      inlineMath: [ ['$','$'], ["\\(","\\)"] ],
      displayMath: [ ['$$','$$'], ["\\[","\\]"] ],
      processEscapes: false
    },
    TeX: {
      extensions: ["AMSmath.js", "AMSsymbols.js"],
      TagSide: "right",
      TagIndent: ".8em",
      MultLineWidth: "85%",
      equationNumbers: {
        autoNumber: "AMS",
      },
      unicode: {
        fonts: "STIXGeneral,'Arial Unicode MS'"
      }
    },
    showProcessingMessages: false
  });
</script>
<script>
  import $ from 'jquery'
  import post from '../service/post'
  import get from '../service/post'
   export default {
     name: 'photoLabelExplain',
     data () {
       return {

       }
     },
     mounted:function(){

     },
     methods:{


       }


   }
</script>

<style>
  @import '../css/explainStyle.css';
  html{
    overflow-y:auto;
  }
</style>
