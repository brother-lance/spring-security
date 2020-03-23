package org.dream.base.core.social.weixin.connect;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.dream.base.core.social.weixin.api.WeiXin;
import org.dream.base.core.social.weixin.api.WeiXinUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * 项目名称：security
 * 类 名 称：WeiXinAdapter
 * 类 描 述：微信适配器
 * 创建时间：2020/3/22 17:04
 * 创 建 人：Lance.WU
 */
@Slf4j
@Getter
@Setter
public class WeiXinAdapter implements ApiAdapter<WeiXin> {


	private String openId;
	
	public WeiXinAdapter() {}
	
	public WeiXinAdapter(String openId){
		this.openId = openId;
	}

	/**
	 * @param api
	 * @return
	 */
	@Override
	public boolean test(WeiXin api) {
		return true;
	}

	/**
	 * @param api
	 * @param values
	 */
	@Override
	public void setConnectionValues(WeiXin api, ConnectionValues values) {
		WeiXinUserInfo profile = api.getWenXinUserInfo(openId);
		values.setProviderUserId(profile.getOpenid());
		values.setDisplayName(profile.getNickname());
		values.setImageUrl(profile.getHeadimgurl());
	}

	/**
	 * @param api
	 * @return
	 */
	@Override
	public UserProfile fetchUserProfile(WeiXin api) {
		return null;
	}

	/**
	 * @param api
	 * @param message
	 */
	@Override
	public void updateStatus(WeiXin api, String message) {
		//do nothing

    }

}
