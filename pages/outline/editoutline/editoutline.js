const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    infoMess: '温馨提示',
    courseid: null,
    outlineid: '',
    title: '第一章 java入门',
    content: 'java junit git', 
    chapterid: 1 ,
    update: 0,
    outlines: [],
    casArray: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29],
    casIndex: 0
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

  // 编辑大纲事件
  outlineTitleInput: function (e) {
    var curtitle = e.detail.value
    this.setData({
      title: curtitle
    })
  },
  outlineContentInput: function (e) {
    var curcontent = e.detail.value
    this.setData({
      content: curcontent
    })
  },

  //删除章节
  delChapter: function () {
    var that = this
    wx.request({
      url: app.globalData.URL + '/teaoperate/deleteOutline',
      data: {
        courseid: that.data.courseid,
        chapterid: that.data.chapterid,
        title: that.data.title
      },
      method: 'GET',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        wx.showToast({
          title: '删除成功',
          icon: 'success',
          duration: 1500
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
      },
    })
  }, 

  //创建课程按钮点击事件，调用参数要用：this.data.参数；
  //设置参数值，要使用this.setData({}）方法
  editCourseOutlineClick: function () {
    console.log(this.data)
    var that = this
    if (this.data.title.length == 0 || this.data.content.length == 0) {
      this.setData({
        infoMess: '温馨提示：标题、内容不能为空！',
      })
    } else {
      this.setData({
        infoMess: '温馨提示',
      })
      that = this
      wx.request({
        url: app.globalData.URL + '/teaoperate/addOutline',
        data: {
          courseid: that.data.courseid,
          chapterid: that.data.chapterid,
          title: that.data.title,
          content: that.data.content,
          outlineid: that.data.outlineid,
          update: that.data.update
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
    var outlinejson = options.outlinejson
    var curcourseid = options.courseid
    var outline = JSON.parse(outlinejson)
    if(outline.id != null){
      this.setData({
        outlineid: outline.id,
        update: 1
      })
    }else{
      this.setData({
        update: 0
      })
    }
    let curcasIndex = this.data.casArray.indexOf(outline.chapterid)
    this.setData({
      courseid: curcourseid,
      title: outline.title ,
      content: outline.content ,
      chapterid: outline.chapterid,
      casIndex: curcasIndex
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