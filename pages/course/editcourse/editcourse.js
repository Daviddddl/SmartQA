const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    infoMess: '温馨提示',
    courseid: null,
    courseName: '',
    coursepassWd: '',
    teacherID: '',
    capacityNum: '',
    startDate: '',
    endDate: ''
  },

  //课程名和密码输入框事件
  courseNameInput: function (e) {
    this.setData({
      courseName: e.detail.value.trim()
    })
  },
  coursepassWdInput: function (e) {
    this.setData({
      coursepassWd: e.detail.value.trim()
    })
  },
  courseCapacityInput: function (e) {
    this.setData({
      capacityNum: e.detail.value.trim()
    })
  },
  teacherInput: function (e) {
    this.setData({
      teacherID: e.detail.value.trim()
    })
  },
  //  点击开始日期组件确定事件  
  startDatePick: function (e) {
    this.setData({
      startDate: e.detail.value
    })
  },
  //  点击结束日期组件确定事件  
  endDatePick: function (e) {
    this.setData({
      endDate: e.detail.value
    })
  },

  //创建课程按钮点击事件，调用参数要用：this.data.参数；
  //设置参数值，要使用this.setData({}）方法
  editCourseBtnClick: function () {
    console.log(this.data)
    if (this.data.courseName.length == 0 || this.data.coursepassWd.length == 0 || this.data.capacityNum.length == 0) {
      this.setData({
        infoMess: '温馨提示：课程名、密码和容量不能为空！',
      })
    } else {
      this.setData({
        infoMess: '温馨提示',
      })
      wx.request({
        url: app.globalData.URI + '/appserver/servlet/CourseServlet',
        data: {
          funcID: "createcourse",
          name: this.data.courseName,
          password: this.data.coursepassWd,
          teacher: this.data.teacherID,
          capacity: this.data.capacityNum,
          startdate: this.data.startDate,
          enddate: this.data.endDate
        },
        method: 'GET',
        header: {
          'content-type': 'application/json' // 默认值
        },
        success: function (res) {
          wx.showToast({
            title: '修改成功',
            icon: 'success',
            duration: 1500
          })
          console.log(res.data);
        },
        fail: function (res) {
          wx.showToast({
            title: '修改失败',
            image: '../../images/icon_fail.png',
            duration: 1500
          })
          console.log(".....fail.....");
        }
      })
    }
    console.log("infoMess: " + this.data.infoMess +
      "\ncourseName: " + this.data.courseName +
      "\ncoursepassWd: " + this.data.coursepassWd +
      "\nteacherID: " + this.data.teacherID +
      "\ncapacityNum: " + this.data.capacityNum +
      "\nstartDate: " + this.data.startDate +
      "\nendDate: " + this.data.endDate + "\n")
  },
  //重置按钮点击事件
  resetBtnClick: function (e) {
    wx.navigateBack({
      delta: 1
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var coursejson = options.coursejson
    var course = JSON.parse(coursejson)
    console.log(course)
    this.setData({
      courseid: course.courseid,
      courseName: course.coursename,
      coursepassWd: course.coursepasswd,
      teacherID: course.teacherid,
      capacityNum: course.capacitynum,
      startDate: course.startdate,
      endDate: course.enddate
    })
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {
    
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    
  },

})