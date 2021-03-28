import Vue from 'vue'
import Router from 'vue-router'
import Login from "../components/Login";
import Home from "../components/Home";
import LastestEssay from "../components/LastestEssay";
import EditEssay from "../components/EditEssay";
import ShowEssay from "../components/ShowEssay";
import FavorEssay from "../components/FavorEssay";
import DraftEssay from "../components/DraftEssay";
import PublishEssay from "../components/PublishEssay";
import CollectedEssay from "../components/CollectedEssay";
import CommentedEssay from "../components/CommentedEssay";
import ModifyInformation from "../components/ModifyInformation";
import Register from "../components/Register";
import EditDraft from "../components/EditDraft";
import UploadImageTest from "../components/UploadImageTest";
import ShowHotEssay from "../components/ShowHotEssay";
import FollowList from "../components/FollowList";
import BloggerEssay from "../components/BloggerEssay";

Vue.use(Router)

const router = new Router({
  routes: [
    // {
    //   path: '/',
    //   name: 'HelloWorld',
    //   component: HelloWorld
    // }
    {
      path: '/',
      redirect: '/login'
    },
    {
      path:'/login',
      name: 'Login',
      component: Login
    },{
      path:'/register',
      name:'Register',
      component: Register
    },
    {
      path:'/home',
      name: 'Home',
      component: Home,
      redirect: '/lastest_essay',
      children:[{
        path:'/lastest_essay',
        name:'LastestEssay',
        component:LastestEssay
      }, {
        path:'/edit_essay',
        name:'EditEssay',
        component: EditEssay
      },{
        path:'/show_essay',
        name:'ShowEssay',
        component: ShowEssay
      },{
        path:'/show_hot_essay',
        name:'ShowHotEssay',
        component: ShowHotEssay
      },{
        path:'/draft_essay',
        name:'DraftEssay',
        component: DraftEssay
      },{
        path:'/follow_list',
        name:'FollowList',
        component: FollowList
      },{
        path:'/blogger_essay',
        name:'BloggerEssay',
        component: BloggerEssay
      }
        ,{
          path:'/publish_essay',
          name:'PublishEssay',
          component: PublishEssay
        }
        ,{
          path:'/favor_essay',
          name:'FavorEssay',
          component: FavorEssay
        }
        ,{
          path:'/commented_essay',
          name:'CommentedEssay',
          component: CommentedEssay
        }
        ,{
          path:'/collected_essay',
          name:'CollectEssay',
          component: CollectedEssay
        },{
          path:'/modify_information',
          name:'ModifyInformation',
          component: ModifyInformation
        },{
          path:'/edit_draft',
          name:'EditDraft',
          component: EditDraft
        },{
          path:'/upload_image_test',
          name:'UploadImageTest',
          component: UploadImageTest
        }
      ]
    }
  ]
})

router.beforeEach((to,from,next)=>
{
  if(to.path==='/register') next();
  else{
    if(to.path==='/login') next();
    if(to.path==='/upload_image_test') next();
    //const tokenStr = window.sessionStorage.getItem('btoken');
    //if(!tokenStr) next('/login');

    const activeId  = window.sessionStorage.getItem('activeId');
    if(!activeId) next('/login');
    else
    next();
  }

})

export default router
