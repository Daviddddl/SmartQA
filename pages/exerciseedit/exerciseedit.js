const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    courseid: null,
    questions: [
      {
        id: 0,
        ques: "第1题：balabala",
        options: [
          { name: "A.nihao", value: "A" },
          { name: "B.wohao", value: "B" },
          { name: "C.tahao", value: "C" },
          { name: "D.nihao", value: "D" }
        ],
        ans: "A"
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
        ans: "B"
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
        ans: "C"
      }
    ]
  },

  nav2editquestion: function(e){
    let quesidx = e.currentTarget.dataset.quesidx;
    var question = this.data.questions[quesidx]
    let ques_str = JSON.stringify(question)
    wx.navigateTo({
      url: '../question/editquestion/editquestion?quesjson=' + ques_str,
    })
  },

  //教师获取课程的题目
  getCourseQuestions: () =>{
    wx.request({
      url: app.globalData.URL + '/teaoperate/listQues',
      data: {
        courseid: this.data.courseid,
        chapters: 1

      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        wx.showToast({
          title: '创建成功',
          icon: 'success',
          duration: 1500
        })
        console.log(res.data);
        
      },
      fail: function (res) {
        wx.showToast({
          title: '创建失败',
          image: '../../images/icon_fail.png',
          duration: 1500
        })
        console.log(".....fail.....");
      }

    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let curcourseid = options.courseid;
    this.setData({
      courseid: curcourseid
    });

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
  nav2addquestion: function(){
    wx.navigateTo({
      url: "../question/addquestion/addquestion",
    })
  }
})