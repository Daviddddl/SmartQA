const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    courseid: null,
    courseName: '',
    coursepassWd: '',
    teacherID: '',
    capacityNum: '',
    startDate: '',
    endDate: ''
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    /*
    wx.request({
      url: app.globalData.URI + '/getStudentCourse',
      data: {
        userID: app.globalData.userInfo.userid,
        courseID: this.data.course.id
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function(res){

      },
      fail: function(res){

      }
    })
    */
    this.setData({
      courseid: options.courseid
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
  editQuestionBank: function(){
    wx.navigateTo({
      url: "../exerciseedit/exerciseedit?courseid=" + this.data.courseid,
    })
  },
  publishQuestions: function () {
    wx.navigateTo({
      url: "../exercisepublish/exercisepublish?courseid=" + this.data.courseid,
    })
  }
 

})