package com.jeesite.modules.love.entity;

import org.apache.commons.lang.StringUtils;

public class Code2Session {

	private String session_key;
	private String openid;
	private String scope;
	private String unionid;
	private String errcode;
	private String errmsg;



	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    
    public boolean isSucc() {
    	return StringUtils.isNotBlank(openid)?true:false;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WeiXinOpenIdInfo [session_key=" + session_key + ", openid=" + openid + ", scope=" + scope + ", unionid=" + unionid + ", errcode="
				+ errcode + ", errmsg=" + errmsg + "]";
	}

	public String getSession_key() {
		return session_key;
	}

	public void setSession_key(String session_key) {
		this.session_key = session_key;
	}
    
    
}
