// pages/managecourse/addcourse.js
var app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    infoMess: '',
    courseID: '',
    courseName: '',
    courseN: '',
    coursepassWd: '',
    coursepassW: '',
    teacher: '',
    teacherID: '',
    capacity: '',
    capacityNum: '',
    startdate: '',
    startDate: '',
    enddate: '',
    endDate: '',
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

  courseCapacityInput: function(e){
    this.setData({
      capacity: e.detail.value
    })
  },
  teacherInput: function (e) {
    this.setData({
      teacher: e.detail.value
    })
  },
  //创建课程按钮点击事件，调用参数要用：this.data.参数；
  //设置参数值，要使用this.setData({}）方法
  createCourseBtnClick: function () {
    if (this.data.courseN.length == 0 || this.data.coursepassW.length == 0 || this.data.capacity.length == 0) {
      this.setData({
        infoMess: '温馨提示：课程名、密码和容量不能为空！',
      })
    } else {
      this.setData({
        courseName: '课程名称：' + this.data.courseN,
        coursepassWd: '选课密码：' + this.data.coursepassW,
        teacherID: '教师ID：' + this.data.teacher,
        capacityNum: '课堂容量：' + this.data.capacity,
        startDate: '开始时间：' + this.data.startdate,
        endDate: '结束时间：' + this.data.enddate,
      })
      wx.request({
        url: 'http://localhost:8080/servlet/CourseServlet',
        data: {
          funcID: 1,
          name: this.data.courseN,
          password: this.data.coursepassW,
          teacher: this.data.teacher,
          capacity: this.data.capacity,
          startdate : this.data.startdate,
          enddate: this.data.enddate
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
        },
        complete: function (res){
          console.log(".....complete.....");
        }
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
      startdate: '',
      enddate: ''
    })
    wx.navigateBack({
    delta:1
    })
  },
  //  点击开始日期组件确定事件  
  startdatePick: function (e) {
    this.setData({
      startdate: e.detail.value
    })
  },
  //  点击结束日期组件确定事件  
  enddatePick: function (e) {
    this.setData({
      enddate: e.detail.value
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