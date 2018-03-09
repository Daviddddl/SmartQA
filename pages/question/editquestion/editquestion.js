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
    infoMess: '温馨提示',
    chapterid: 1,
    casArray: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29],
    casIndex: 0
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

  //下拉框选择事件
  bindCasPickerChange: function (e) {
    let outlineidx = e.currentTarget.dataset.outlineidx
    let curcasindex = e.detail.value
    let curchapterid = this.data.casArray[curcasindex]
    this.setData({
      chapterid: curchapterid,
      casIndex: e.detail.value
    })
    console.log(this.data)
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
      var option_list = []
      for (var option of this.data.options) {
        option_list.push(option.name)
      }
      var options_str = option_list.join("<EOF>")
      console.log(options_str)
      var that = this
      wx.request({
        url: app.globalData.URL + '/teaoperate/updateQues',
        data: {
          quesid: that.data.quesid,
          ques: that.data.ques,
          options: options_str,
          ans: that.data.answer,
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

  },
  delQuestionClick: function(e){
    var that = this
    wx.request({
      url: app.globalData.URL + '/teaoperate/deleteQuesByID',
      data: {
        quesid: that.data.quesid,
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        wx.showToast({
          title: '删除成功',
          icon: 'success',
          duration: 1500
        })
        wx.navigateBack({
          delta: 1
        })
        console.log(res.data);
      },
      fail: function (res) {
        wx.showToast({
          title: '删除失败',
          image: '../../images/icon_fail.png',
          duration: 1500
        })
        console.log(".....fail.....");
      }
    })
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
    console.log(quesjson)
    var curquestion = JSON.parse(quesjson)
    this.setData({
      quesid: curquestion.id,
      ques: curquestion.ques,
      options: curquestion.options,
      answer: curquestion.ans,
      chapterid: curquestion.chapterid
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