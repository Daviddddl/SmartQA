//index.js


Page({
  data: {
    list: [
      {
        id: 'mycourses',
        name: '正在进行的课程',
        open: false,
        pages: ['课程1', '课程2', '课程3']
      },
      {
        id: 'allcourses',
        name: '所有课程',
        open: false,
        pages: ['课程1', '课程2', '课程3', '课程4', '课程5']
      }
    ]
  },
  onLoad: function(){

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
  nav2editcourse: function(){
    wx.navigateTo({
      url: '../addcourse/addcourse',
   })
  }
})
