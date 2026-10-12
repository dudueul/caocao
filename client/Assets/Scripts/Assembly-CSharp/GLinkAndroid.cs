using UnityEngine;

public class GLinkAndroid : IGLink
{
	private class OnClickAppSchemeBannerListener : AndroidJavaProxy
	{
		public OnClickAppSchemeBannerListener()
			: base((string)null)
		{
		}

		private void onClickAppSchemeBanner(string appScheme)
		{
		}
	}

	private class OnSdkStartedListener : AndroidJavaProxy
	{
		public OnSdkStartedListener()
			: base((string)null)
		{
		}

		private void onSdkStarted()
		{
		}
	}

	private class OnSdkStoppedListener : AndroidJavaProxy
	{
		public OnSdkStoppedListener()
			: base((string)null)
		{
		}

		private void onSdkStopped()
		{
		}
	}

	private class OnLoggedInListener : AndroidJavaProxy
	{
		public OnLoggedInListener()
			: base((string)null)
		{
		}

		private void onLoggedIn(bool success)
		{
		}
	}

	private class OnJoinedListener : AndroidJavaProxy
	{
		public OnJoinedListener()
			: base((string)null)
		{
		}

		private void onJoined()
		{
		}
	}

	private class OnPostedArticleListener : AndroidJavaProxy
	{
		public OnPostedArticleListener()
			: base((string)null)
		{
		}

		private void onPostedArticle(int menuId, int imageCount, int videoCount)
		{
		}
	}

	private class OnPostedCommentListener : AndroidJavaProxy
	{
		public OnPostedCommentListener()
			: base((string)null)
		{
		}

		private void onPostedComment(int articleId)
		{
		}
	}

	private class OnWidgetScreenshotClickListener : AndroidJavaProxy
	{
		public OnWidgetScreenshotClickListener()
			: base((string)null)
		{
		}

		private void onScreenshotClick()
		{
		}
	}

	private class OnRecordFinishListener : AndroidJavaProxy
	{
		public OnRecordFinishListener()
			: base((string)null)
		{
		}

		private void onRecordFinished(string uri)
		{
		}
	}

	private class OnVotedListener : AndroidJavaProxy
	{
		public OnVotedListener()
			: base((string)null)
		{
		}

		private void onVoted(int articleId)
		{
		}
	}

	private class OnEndStreamingLiveListener : AndroidJavaProxy
	{
		public OnEndStreamingLiveListener()
			: base((string)null)
		{
		}

		private void onEndStreamingLive(int viewCount, int likeCount)
		{
		}
	}

	private class OnEndWatchingLiveListener : AndroidJavaProxy
	{
		public OnEndWatchingLiveListener()
			: base((string)null)
		{
		}

		private void onEndWatchingLive(int seconds)
		{
		}
	}

	private AndroidJavaClass glinkClass;

	private AndroidJavaObject currentActivity;

	private static void showToast(string message)
	{
	}

	public void executeHome()
	{
	}

	public void executeArticle(int articleId)
	{
	}

	public void executeArticlePost()
	{
	}

	public void executeArticlePostWithImage(string filePath)
	{
	}

	public void executeArticlePostWithVideo(string filePath)
	{
	}

	public void executeMore()
	{
	}

	public void syncGameUserId(string gameUserId)
	{
	}

	public void startWidget()
	{
	}

	public void stopWidget()
	{
	}

	public void setUseWidgetVideoRecord(bool useVideoRecord)
	{
	}

	public void setUseWidgetScreenShot(bool useScreenShot)
	{
	}

	public void setShowWidgetWhenUnloadSDK(bool useWidget)
	{
	}

	public string getCurrentChannelCode()
	{
		return null;
	}

	public void setChannelCode(string channelCode)
	{
	}

	public void setThemeColor(string themeColorCSSString)
	{
	}

	public void setThemeColor(string themeColorCSSString, string backgroundCSSString)
	{
	}

	public void setWidgetStartPosition(bool isLeft, int heightPercentage)
	{
	}
}
