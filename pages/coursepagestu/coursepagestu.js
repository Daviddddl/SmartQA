const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    courseid: null,
    coursename: 'java从入门到放弃',
    teacherremark: 'ddl',
    startdate: '2018-3-1',
    enddate: '2018-5-1',
    outlines: [
      { title: '第一章 java入门', content: 'java junit git', chapterid: 1 },
      { title: '第二章 java多线程', content: 'multithreads', chapterid: 2 }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
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
    let curcourseid = this.data.courseid
    console.log(curcourseid)
    var that = this
    wx.request({
      url: app.globalData.URL + '/stuoperate/courseDetail',
      data: {
        courseid: curcourseid,
        userid: 17
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        var course = res.data.result[0]
        that.setData({
          coursename: course.coursename,
          startdate: course.starttime,
          enddate: course.endtime,
          teacherremark: course.teacher_remark,
          outlines: course.outlines
        })
        wx.showToast({
          title: '加载成功',
          icon: 'success',
          duration: 1500
        })
        // console.log(res.data);
      },
      fail: function (res) {
        wx.showToast({
          title: '加载失败',
          image: '../../images/icon_fail.png',
          duration: 1500
        })
        console.log(".....fail.....");
      }
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

  },
  registerAttendance: function () {
    wx.navigateTo({
      url: "../exercise/exerciseedit/exerciseedit",
    })
  },
  answerQuestions: function () {
    wx.navigateTo({
      url: "../exercise/exerciseanswering/exerciseanswering",
    })
  },
  historyAnswering: function () {
    wx.navigateTo({
      url: "../exercise/exercisehistory/exercisehistory",
    })
  }


})