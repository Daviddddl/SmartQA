const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    questions: [
      { id: 0,
        ques: "第1题：balabala", 
        ans: [
          { name: "A.nihao", value: "A" },
          { name: "B.wohao", value: "B" },
          { name: "C.tahao", value: "C" },
          { name: "D.nihao", value: "D" }
        ] },
      {
        id: 1,
        ques: "第2题：balabala", 
        ans: [
          { name: "A.nihao", value: "A" },
          { name: "B.wohao", value: "B" },
          { name: "C.tahao", value: "C" },
          { name: "D.nihao", value: "D" }
        ]
      },
      {
        id: 2,
        ques: "第3题：balabala", 
        ans: [
          { name: "A.nihao", value: "A" },
          { name: "B.wohao", value: "B" },
          { name: "C.tahao", value: "C" },
          { name: "D.nihao", value: "D" }
        ]
      }
    ]
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {

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

  },
  radioChange: function (e) {
   // console.log('radio发生change事件，携带value值为：', e.detail.value);
    
    var quesidx = e.currentTarget.dataset.quesidx;
    console.log(quesidx)
    var newquestions = this.data.questions;
    var radioItems = newquestions[quesidx].ans;
    for (var i = 0, len = radioItems.length; i < len; ++i) {
      radioItems[i].checked = radioItems[i].value == e.detail.value;
    }
    newquestions[quesidx].ans = radioItems
    this.setData({
      questions: newquestions
    });
    //console.log(this.data.questions)
  },
})