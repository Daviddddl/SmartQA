// pages/managecourse/addcourse.js
var app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    infoMess: '',
    courseName: '',
    courseN: '',
    coursepassWd: '',
    coursepassW: '',
    courseId: ''
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
  //创建课程按钮点击事件，调用参数要用：this.data.参数；
  //设置参数值，要使用this.setData({}）方法
  createCourseBtnClick: function () {
    if (this.data.courseN.length == 0 || this.data.coursepassW.length == 0) {
      this.setData({
        infoMess: '温馨提示：用户名和密码不能为空！',
      })
    } else {
      this.setData({
        infoMess: '课程创建成功！',
        courseName: '课程名称：' + this.data.courseN,
        coursepassWd: '选课密码：' + this.data.coursepassW,
        courseId: '课程唯一ID: '
      })
    }
  },
  //重置按钮点击事件
  resetBtnClick: function (e) {
    this.setData({
      infoMess: '',
      courseName: '',
      courseN: '',
      coursepassWd: '',
      coursepassW: '',
    })
    wx.navigateBack({
    delta:1
    })
  },
  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function () {
    console.log('onLoad')
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