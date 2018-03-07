const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
    courseid: null,
    coursename: 'java从入门到放弃',
    coursepasswd: '123',
    teacherid: '16',
    capacitynum: '40',
    startdate: '2018-3-1',
    enddate: '2018-5-1',
    outlines: [
      { title: '第一章 java入门', content: 'java junit git', chapterid: 1 },
      { title: '第二章 java多线程', content: 'multithreads', chapterid: 2 }
    ]
   
  },

  nav2editcourse: function(e){
    var course = {};
    course.courseid = this.data.courseid
    course.coursename = this.data.coursename
    course.coursepasswd = this.data.coursepasswd
    course.capacitynum = this.data.capacitynum
    course.teacherid = this.data.teacherid
    course.startdate = this.data.startdate
    course.enddate = this.data.enddate
    var coursejson = JSON.stringify(course)
    wx.navigateTo({
      url: '../course/editcourse/editcourse?coursejson=' + coursejson,
    })
  },

  nav2editoutline: function(e) {
    let outidx = e.currentTarget.dataset.outidx;
    let courseid = this.data.courseid;
    var outline = this.data.outlines[outidx]
    let outline_str = JSON.stringify(outline)
    wx.navigateTo({
      url: '../outline/editoutline/editoutline?outlinejson=' + outline_str + '&courseid=' + courseid,
    })
  },

  //添加删除章节
  addChapter: function () {
    var lists = this.data.outlines;
    var newData = {title: "新章节", content: '', chapterid: 1};
    lists.push(newData);//实质是添加lists数组内容，使for循环多一次  
    this.setData({
      outlines: lists,
    })
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
      url: app.globalData.URL + '/teaoperate/listCourseDetail',
      data: {
        courseid: curcourseid
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        var course = res.data.result[0]
        that.setData({
          coursename: course.name,
          coursepasswd: course.password,
          capacitynum: course.capacity,
          startdate: course.starttime,
          enddate: course.endtime
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
    //获取课程大纲信息
    wx.request({
      url: app.globalData.URL + '/teaoperate/listAllOutline',
      data: {
        courseid: curcourseid
      },
      method: 'GET',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data)
        var curoutlines_array = res.data.result
        var curoutlines = []
        for (var outlines_list of curoutlines_array){
          if(outlines_list != null){
            for (var outline of outlines_list){
              curoutlines.push(outline)
            }
          }
        }
        console.log(curoutlines)
        if(curoutlines != null){
          that.setData({
            outlines: curoutlines
          })
        }else{
          that.setData({
            outlines: []
          })
        }
        
        // wx.showToast({
        //   title: '加载成功',
        //   icon: 'success',
        //   duration: 1500
        // })
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
  editQuestionBank: function(){
    wx.navigateTo({
      url: "../exercise/exerciseedit/exerciseedit?courseid=" + this.data.courseid,
    })
  },
  publishQuestions: function () {
    wx.navigateTo({
      url: "../exercise/exercisepublish/exercisepublish?courseid=" + this.data.courseid,
    })
  }
 

})