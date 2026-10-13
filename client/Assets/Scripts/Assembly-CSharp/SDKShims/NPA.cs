// [RE] NPA / TOY SDK shims — the original glue (NPAController, CaocaoToyPush*,
// NPAListener/*) compiled against the TOYSDK.dll, which Unity 6 rejects
// ("Unloading broken assembly"). These shims recreate the EXACT type shapes
// and NAMESPACES from Il2CppDumper's dump.cs (fix loop 17: per-type namespace
// map verified — NXPToyCloseResult ∈ NPA.Promotion, the user/login result
// classes ∈ NPA.Auth). The SDK is functionally dead (replaced by the
// guest-auth backend); this file keeps the dead glue compiling.
using System;

namespace SimpleJSON
{
	// [RE] minimal JSONNode — NPAController holds loginJson/getUserInfoJson
	// (declaration only; the runtime JSON path is LitJson in the rebuild)
	public class JSONNode
	{
		public virtual JSONNode get_Item(int aIndex) { return this; }

		public virtual JSONNode get_Item(string aKey) { return this; }

		public override string ToString() { return "{}"; }
	}
}

namespace NPA
{
	public enum NPLoginType
	{
		NPLoginTypeDefault,
	}

	public enum NPCountry
	{
	}

	public enum NPLocale
	{
		EN_AU,
	}

	public enum NPNotificationTime
	{
	}

	public enum NPErrorCode
	{
	}

	public class Toy
	{
	}

	public class NPOptions
	{
	}

	public class NPResult
	{
	}

	public class NXPResult : NPResult
	{
	}

	public class NXPToyResult : NXPResult
	{
	}

	public class NPCloseResult
	{
	}

	// [RE] CaocaoToyPushData's base ctor: base(0, null, null, null, 0)
	public class NPNotificationData
	{
		public NPNotificationData(int notificationID, string message, string meta,
			string deepLink, int pushType)
		{
		}
	}

	public interface INPListener
	{
		void OnResult(NPResult result);
	}

	public interface INPBannerListener
	{
		void OnBannerClick(string landInfo);
		void OnBannerFailed(NPResult npResult);
		void OnBannerDismiss();
	}

	// [RE] dump.cs: INPEndingBannerListener (with "ing") ∈ NPA, TypeDefIndex 3029;
	// the EndingBannerListener stub spells it INPEndBannerListener (original
	// code typo preserved) — declare BOTH spellings.
	public interface INPEndingBannerListener
	{
		void OnEndingBannerClick(string landInfo);
		void OnEndingBannerFailed(NPResult npResult);
		void OnEndingBannerDismiss();
		void OnEndingBannerExit();
	}

	public interface INPEndBannerListener
	{
		void OnEndingBannerClick(string landInfo);
		void OnEndingBannerFailed(NPResult npResult);
		void OnEndingBannerDismiss();
		void OnEndingBannerExit();
	}

	public interface INPOnCloseListener
	{
		void OnClose(NPCloseResult result);
	}

	public interface INPRuntimePermissionListener
	{
		void OnRequestPermissionsResult(int requestCode, string[] permissions, int[] grantResults);
		void OnRequestPermissionsRunTimeResult(int requestCode, string[] permissions, int[] grantResults);
	}

	public interface INPRecvNotificationListener
	{
		void OnRecvNotification(SimpleJSON.JSONNode recvNotification);
	}

	public interface INPPlateListener
	{
		void OnActionPerformedResult(NPResult result);
	}
}

namespace NPA.Auth
{
	// [RE] dump.cs: the login/user result classes live HERE (TypeIndexes in
	// NPA.Auth) — LoginListener/GetCountryFromServerListener/GetUserInfoListener
	// have `using NPA.Auth;`
	public class NXPToyLoginResult : NPA.NXPToyResult
	{
	}

	public class NXPToyCountryResult : NPA.NXPToyResult
	{
	}

	public class NXPToyUserInfoResult : NPA.NXPToyResult
	{
	}
}

namespace NPA.Promotion
{
	// [RE] dump.cs: NXPToyCloseResult ∈ NPA.Promotion (TypeDefIndex 3206) —
	// NoticeListener has `using NPA.Promotion;`
	public class NXPToyCloseResult : NPA.NXPResult
	{
	}
}
