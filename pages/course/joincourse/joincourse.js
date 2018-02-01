// pages/joincourse/joincourse/.js
//获取应用实例
const app = getApp()
Page({

  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    infoMess: '',
    courseName: '',
    courseN: '',
    stuID: ''
  },
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function (e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  //课程名和密码输入框事件
  courseNameInput: function (e) {
    this.setData({
      courseN: e.detail.value
    })
  },
  coursepassWdInput: function (e) {
    this.setData({
      coursepassW: e.detail.value
    })
  },

  stuIDInput: function(e){
    this.setData({
      stuID: e.detail.value
    })
  },

  //创建课程按钮点击事件，调用参数要用：this.data.参数；
  //设置参数值，要使用this.setData({}）方法
  joinCourseBtnClick: function () {
    if (this.data.courseN.length == 0 || this.data.coursepassW.length == 0) {
      this.setData({
        infoMess: '温馨提示：课程名和密码不能为空！',
      })
    } else {
      this.setData({
        infoMess: '课程加入成功！',
        courseName: '课程名称：' + this.data.courseN,
      })
      wx.request({
        url: 'http://localhost:8080/servlet/CourseServlet',
        data: {
          funcID: 2,
          name: this.data.courseN,
          password: this.data.coursepassW,
          stuID: this.data.stuID
        },
        method: 'GET',
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: function (res) {
          console.log(res.data);
        },
        fail: function (res) {
          console.log(".....fail.....");
        }
      })
    }
  },
  //重置按钮点击事件
  resetBtnClick: function (e) {
    this.setData({
      infoMess: '',
      courseName: '',
      courseN: ''
    })
    wx.navigateBack({
      delta: 1
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

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
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})