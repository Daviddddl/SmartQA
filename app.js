//app.js
App({
  getAllCourses: function (userID) {
    wx.request({
      url: this.globalData.URI + '/getAllCourses',
      data: {
        userID: userID
      },
      method: 'GET',
      header: { 'content-type': 'application/json' },
      success: function (res) {
        if (res.statusCode === 200) {
          return res.data
        } else {
          console.log("get all courses failed.")
        }
      },
      fail: function (res) {
        console.log("get all courses failed.")
      }
    })
  },
  globalData: {
    userInfo: {},
    URL: 'https://www.doveminr.com/smartQA'
  },
  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    /*
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
        if (res.code) {
          //发起网络请求
          //获取openId
          wx.request({
            url: this.globalData.URI + '/onLogin',
            data: {
              code: res.code
            },
            method: 'GET',
            header: { 'content-type': 'application/json' },
            success: function (openIdRes) {
              console.info("登录成功返回的openId：" + openIdRes.data.openid);
              this.globalData.userInfo.openId = openIdRes.data.openid;
              // 判断openId是否获取成功
              if (openIdRes.data.openid != null & openIdRes.data.openid != undefined) {
                // 有一点需要注意 询问用户 是否授权 那提示 是这API发出的
                wx.getUserInfo({
                  success: function (data) {
                    // 自定义操作
                    // 绑定数据，渲染页面
                    
                  },
                  fail: function (failData) {
                    console.info("用户拒绝授权");
                  }
                });
              } else {
                console.info("获取用户openId失败");
              }
            },
            fail: function (error) {
              console.info("获取用户openId失败");
              console.info(error);
            }
          })
        }
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }else{
          //授权失败
          console.log("用户信息授权失败。")
        }
      }
    })*/
  }
  
})