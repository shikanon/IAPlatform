// 静态编译加载
//import topMenu_view from '../view/topMenu.vue'
// 懒加载

const topMenu_view = resolve => require(['../view/topMenu.vue'], resolve);
const itemShow_view = resolve => require(['../view/itemShow.vue'], resolve);
const intellLaboratory_view = resolve => require(['../view/intellLaboratory.vue'], resolve);
const item_view = resolve => require(['../view/item.vue'], resolve);
const photoLabel_view = resolve => require(['../view/photoLabel.vue'], resolve);
const photoStyle_view = resolve => require(['../view/photoStyle.vue'], resolve);
const faceRecognition_view = resolve => require(['../view/faceRecognition.vue'], resolve);
const autoEngComRead_view = resolve => require(['../view/autoEngComRead.vue'], resolve);
const autoAbstracting_view = resolve => require(['../view/autoAbstracting.vue'], resolve);
const explain_view = resolve => require(['../view/explain.vue'], resolve);
const photoLabelExplain_view = resolve => require(['../view/photoLabelExplain.vue'], resolve);
const newConsultOne_view = resolve => require(['../view/newConsultOne.vue'], resolve);
const newConsultTwo_view = resolve => require(['../view/newConsultTwo.vue'], resolve);
const newConsultThree_view = resolve => require(['../view/newConsultThree.vue'], resolve);
const useHelpOne_view = resolve => require(['../view/useHelpOne.vue'], resolve);
const useHelpTwo_view = resolve => require(['../view/useHelpTwo.vue'], resolve);
const useHelpThree_view = resolve => require(['../view/useHelpThree.vue'], resolve);
const comproblemOne_view = resolve => require(['../view/comproblemOne.vue'], resolve);
const comproblemTwo_view = resolve => require(['../view/comproblemTwo.vue'], resolve);
const comproblemThree_view = resolve => require(['../view/comproblemThree.vue'], resolve);
const waitForRecognition_view = resolve => require(['../view/waitForRecognition.vue'], resolve);
const comproblemFour_view = resolve => require(['../view/comproblemFour.vue'], resolve);
const imgObjExtraction_view = resolve => require(['../view/imgObjExtraction.vue'], resolve);
const peoObjRecognition_view = resolve => require(['../view/peoObjRecognition.vue'], resolve);

export const topMenu_route = {
  path: '/topMenu',
  component: topMenu_view,
  children:[
    {
      path:'itemShow',
      component:itemShow_view,
    },
    {
      path:'intellLaboratory',
      component:intellLaboratory_view,
    },
    {
      path: 'explain',
      component:explain_view,
      children:[
        {
          path: 'photoLabelExplain',
          component: photoLabelExplain_view,
        },
        {
          path: 'newConsultOne',
          component: newConsultOne_view,
        },
        {
          path:'newConsultTwo',
          component: newConsultTwo_view,
        },
        {
          path:'newConsultThree',
          component: newConsultThree_view,
        },
        {
          path:'useHelpOne',
          component:useHelpOne_view,
        },
        {
          path:'useHelpTwo',
          component:useHelpTwo_view,
        },
        {
          path:'useHelpThree',
          component:useHelpThree_view,
        },
        {
          path:'comproblemOne',
          component:comproblemOne_view,
        },
        {
          path:'comproblemTwo',
          component:comproblemTwo_view,
        },
        {
          path:'comproblemThree',
          component:comproblemThree_view,
        },
        {
          path:'comproblemFour',
          component:comproblemFour_view,
        }
      ],
    },
    {
      path: 'item',
      component: item_view,
      children:[
        {
          path:'photoLabel',
          component:photoLabel_view,
        },
        {
          path:'photoStyle',
          component:photoStyle_view,
        },
        {
          path:'faceRecognition',
          component:faceRecognition_view,
        },
        {
          path:'waitForRecognition',
          component:waitForRecognition_view,
        },
        {
          path:'autoEngComRead',
          component:autoEngComRead_view,
        },
        {
          path:'autoAbstracting',
          component:autoAbstracting_view,
        },
        {
          path:'imgObjExtraction',
          component:imgObjExtraction_view,

        },
        {
          path:'peoObjRecognition',
          component:peoObjRecognition_view,
        },
      ],
    }
  ]
}
