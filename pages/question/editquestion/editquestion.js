const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    courseid: null,
    quesid: null,
    ques: "",
    options: [{}, {}, {}, {}],
    answer: "",
    infoMess: '温馨提示'
  },

  addOption: function () {
    var lists = this.data.options;
    var newData = {};
    lists.push(newData);//实质是添加lists数组内容，使for循环多一次  
    this.setData({
      options: lists,
    })
  },
  delOption: function () {
    var lists = this.data.options;
    lists.pop();      //实质是删除lists数组内容，使for循环少一次  
    this.setData({
      options: lists,
    })
  },

  quesNameInput: function (e) {
    this.setData({
      ques: e.detail.value.trim()
    })
  },
  quesOptionInput: function (e) {
    var optidx = e.currentTarget.dataset.optidx;
    var newoptions = this.data.options
    var option = this.data.options[optidx]
    option.name = e.detail.value.trim()
    newoptions[optidx] = option

    this.setData({
      options: newoptions
    });
  },
  quesAnswerInput: function (e) {
    this.setData({
      answer: e.detail.value.trim()
    })
  },

  editQuestionClick: function() {
    if (this.data.ques.length == 0 || this.data.answer.length == 0) {
      this.setData({
        infoMess: '温馨提示：题目或答案不能为空！',
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
            title: '添加成功',
            icon: 'success',
            duration: 1500
          })
          console.log(res.data);
        },
        fail: function (res) {
          wx.showToast({
            title: '添加失败',
            image: '../../images/icon_fail.png',
            duration: 1500
          })
          console.log(".....fail.....");
        },
        complete: function (res) {
          console.log(".....complete.....");
        }
      })
    }

  },
  delQuestionClick: function(e){

  },
  //重置按钮点击事件
  resetBtnClick: function (e) {
    this.setData({
      ques: "",
      options: [{}, {}, {}, {}],
      answer: "",
      infoMess: '温馨提示'
    })
    wx.navigateBack({
      delta: 1
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let quesjson = options.quesjson;
    var curquestion = JSON.parse(quesjson)
    this.setData({
      quesid: curquestion.id,
      ques: curquestion.ques,
      options: curquestion.options,
      answer: curquestion.ans
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