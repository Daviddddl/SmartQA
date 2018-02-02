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
        pages: ['课程a', '课程b']
      },
      {
        id: 'allcourses',
        name: '所有课程',
        open: false,
        pages: ['课程a', '课程b', '课程c']
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
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
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
    
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    
  }
})