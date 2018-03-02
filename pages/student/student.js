const app = getApp()

Page({
  /**
   * 页面的初始数据
   */
  data: {
    inputShowed: false,
    inputVal: "",
    list: [
      {
        id: 'ingcourses',
        name: '正在进行的课程',
        open: false,
        pages: [
          { id: 1, name: '课程a' },
          { id: 2, name: '课程b' }
        ]
      },
      {
        id: 'allcourses',
        name: '所有课程',
        open: false,
        pages: [
          { id: 1, name: '课程a' },
          { id: 2, name: '课程b' },
          { id: 3, name: '课程c' },
          { id: 4, name: '课程d' }
        ]
      }
    ]
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
  //搜索框事件
  showInput: function () {
    console.log("in showInput")
    this.setData({
      inputShowed: true
    });
  },
  hideInput: function () {
    console.log("in hideInput")
    this.setData({
      inputVal: "",
      inputShowed: false
    });
  },
  clearInput: function () {
    console.log("in clearInput")
    this.setData({
      inputVal: ""
    });
  },
  inputTyping: function (e) {
    console.log("in inputTyping")
    this.setData({
      inputVal: e.detail.value
    });
    console.log(this.data.inputVal)
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: (options)=>{

  },
  onShow: function () {
    var that = this
    wx.request({
      url: app.globalData.URL + '/stuoperate/listMyCourse',
      data: {
        userid: 17,
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
        console.log("...show student courses success...")
      },
      fail: function (res) {
        wx.showToast({
          title: '加载失败',
          image: '../../images/icon_fail.png',
          duration: 1500
        })
        console.log(".....show student courses fail.....");
      },

    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    var that = this
    wx.request({
      url: app.globalData.URL + '/stuoperate/listMyCourse',
      data: {
        userid: 17,
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
        console.log("...refresh student courses success...")
      },
      fail: function (res) {
        wx.showToast({
          title: '加载失败',
          image: '../../images/icon_fail.png',
          duration: 1500
        })
        console.log(".....refresh student courses fail.....");
      },

    })
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})