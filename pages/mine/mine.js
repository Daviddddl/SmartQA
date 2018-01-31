//mine.js
//获取应用实例
const app = getApp()

Page({
  data: {
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
    infoMess: '',
    remarkName: '',
    remarkN: '',
    nickName: '',
    gender: '',
    lang: '',
    city: '',
    province: '',
    country: ''
  },
  
  onLoad: function () {
    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
      // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
      // 所以此处加入 callback 以防止这种情况
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
      // 在没有 open-type=getUserInfo 版本的兼容处理
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function (e) {
    console.log(e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  },
  remarkNameInput: function (e) {
    this.setData({
      remarkN: e.detail.value
    })
  },
  createuser: function () {
    if (this.data.remarkN.length == 0) {
      this.setData({
        infoMess: '温馨提示：用户名和密码不能为空！',
      })
    }else{
      this.setData({
        infoMess: '创建用户成功！',
        nickName: '您的昵称：' + app.globalData.userInfo.nickName,
        remarkName: '备注名称：' + this.data.remarkN,
        gender: '性别：' + (app.globalData.userInfo.gender == 1?'男':'女'),
        lang: '语言：'+app.globalData.userInfo.language,
        city: '城市：'+app.globalData.userInfo.city,
        province: '省会：' + app.globalData.userInfo.province,
        country: '国家：'+app.globalData.userInfo.country
      }),
      wx.request({
          url: 'http://localhost:8080/servlet/UserServlet',
          data: {
            nickname: app.globalData.userInfo.nickName,
            remark: this.data.remarkN,
            gender: app.globalData.userInfo.gender,
            lang: app.globalData.userInfo.language,
            city: app.globalData.userInfo.city,
            province: app.globalData.userInfo.province,
            country: app.globalData.userInfo.country,
            avatarUrl: app.globalData.userInfo.avatarUrl,
          },
          method: 'GET',
          header: {
            'content-type': 'application/json' // 默认值
          },
          success: function (res) {
            console.log(res.data);
          },
          fail: function (res) {
            console.log(".....fail.....");
          }
      })
    }
  }
})
