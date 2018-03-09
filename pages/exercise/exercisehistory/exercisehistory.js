const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    courseid: null,
    questions: [
      { id: 0,
        ques: "第1题：balabala", 
        options: [
          { name: "A.nihao", value: "A" },
          { name: "B.wohao", value: "B" },
          { name: "C.tahao", value: "C" },
          { name: "D.nihao", value: "D" }
        ],
        answer: 'A',
        stuanswer: 'B',
        right: flase
      },
      {
        id: 1,
        ques: "第2题：balabala", 
        options: [
          { name: "A.nihao", value: "A" },
          { name: "B.wohao", value: "B" },
          { name: "C.tahao", value: "C" },
          { name: "D.nihao", value: "D" }
        ],
        answer: 'A'
      },
      {
        id: 2,
        ques: "第3题：balabala", 
        options: [
          { name: "A.nihao", value: "A" },
          { name: "B.wohao", value: "B" },
          { name: "C.tahao", value: "C" },
          { name: "D.nihao", value: "D" }
        ],
        answer: 'A'
      }
    ]
  },

  radioChange: function (e) {
    // console.log('radio发生change事件，携带value值为：', e.detail.value);

    var quesidx = e.currentTarget.dataset.quesidx;
    console.log(quesidx)
    var newquestions = this.data.questions;
    var radioItems = newquestions[quesidx].options;
    for (var i = 0, len = radioItems.length; i < len; ++i) {
      radioItems[i].checked = radioItems[i].name == e.detail.value;
    }
    let quesid = newquestions[quesidx].id
    newquestions[quesidx].options = radioItems
    var answer = { id: quesid, choice: e.detail.value }
    this.setData({
      questions: newquestions,
    });
  },

  goBack: function(){
    wx.navigateBack({
      delta: 1
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let curcourseid = options.courseid
    this.setData({
      courseid: curcourseid
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