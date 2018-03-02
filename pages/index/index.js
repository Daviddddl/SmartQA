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
  
  onLoad: (options) => {

  },

  onShow: function(){
    // console.log("show data:")
    var that = this
    wx.request({
      url: app.globalData.URL + '/teaoperate/listMyOwnCourse',
      data: {
        userid: 16,
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        var allcourses = res.data.result
        var activecourses = allcourses.filter((course)=>course.isactive == "1")
        var newlist = that.data.list
        // console.log(newlist)
        newlist[0].pages = activecourses
        newlist[1].pages = allcourses
        that.setData({
          list: newlist
        })
        console.log("... show teacher courses success...")
      },
      fail: function (res) {
        wx.showToast({
          title: '加载失败',
          image: '../../images/icon_fail.png',
          duration: 1500
        })
        console.log(".....show teacher courses fail.....");
      },

    })
  },
  onPullDownRefresh: function(){
    var that = this
    wx.request({
      url: app.globalData.URL + '/teaoperate/listMyOwnCourse',
      data: {
        userid: 16,
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        var allcourses = res.data.result
        var activecourses = allcourses.filter((course) => course.isactive == "1")
        var newlist = that.data.list
        // console.log(newlist)
        newlist[0].pages = activecourses
        newlist[1].pages = allcourses
        that.setData({
          list: newlist
        })
        console.log("...refresh teacher courses success...")
      },
      fail: function (res) {
        wx.showToast({
          title: '加载失败',
          image: '../../images/icon_fail.png',
          duration: 1500
        })
        console.log(".....refresh teacher courses fail.....");
      },

    })
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
      url: '../course/addcourse/addcourse',
   })
  }
})
