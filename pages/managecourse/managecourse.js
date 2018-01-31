Page({
  data: {
  },
  onLoad: function () {
    this.setData({
      name: "addcourse"
    })
  },
  //重置按钮点击事件
  resetBtnClick: function (e) {
    wx.navigateBack({
      delta: 1
    })
  },
})