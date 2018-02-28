//index.js
const app = getApp()

Page({
  data: {
    list: [
      {
        id: 'ingcourses',
        name: '正在进行的课程',
        open: false,
        pages: [
          {id: 1, name: '课程1'}, 
          {id: 2, name: '课程2'},
          {id: 3, name: '课程3'}
        ]
      },
      {
        id: 'allcourses',
        name: '所有课程',
        open: false,
        pages: [
          { id: 1, name: '课程1' },
          { id: 2, name: '课程2' },
          { id: 3, name: '课程3' },
          { id: 4, name: '课程4' },
          { id: 5, name: '课程5' }
        ]
      }
    ]
  },
  getAllCourses: () => {
    wx.request({
      url: app.globalData.URI + '/appserver/servlet/CourseServlet',
      data: {
        funcID: "listallcourse",
        userID: 1,
        nickname: "DAVIDDDL_狄",
        remark: "123",
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        wx.showToast({
          title: '创建成功',
          icon: 'success',
          duration: 1500
        })
        console.log(res.data);
      },
      fail: function (res) {
        wx.showToast({
          title: '创建失败',
          image: '../../images/icon_fail.png',
          duration: 1500
        })
        console.log(".....fail.....");
      },
      complete: function (res) {
        console.log(".....complete.....");
      }
    
    })
  },

  onLoad: function(){

  },
  onPullDownRefresh: function(){
    this.getAllCourses()
  },
  
  kindToggle: function (e) {
    var id = e.currentTarget.id, list = this.data.list;
    for (var i = 0, len = list.length; i < len; ++i) {
      if (list[i].id == id) {
        list[i].open = !list[i].open
      } else {
        list[i].open = false
     }
    }
    this.setData({
      list: list
    });
  },
  nav2addcourse: function(){
    wx.navigateTo({
      url: '../addcourse/addcourse',
   })
  }
})
