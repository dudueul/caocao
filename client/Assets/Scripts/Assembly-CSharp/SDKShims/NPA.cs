// [RE] NPA / TOY SDK shims — the original glue (NPAController, CaocaoToyPush*,
// NPAListener/*) compiled against the TOYSDK.dll, which Unity 6 rejects
// ("Unloading broken assembly"). These shims recreate the EXACT type shapes
// the glue stubs reference, sourced from Il2CppDumper's dump.cs
// (namespaces NPA*, NPAListener) — the SDK is functionally dead (replaced by
// the guest-auth backend); this file keeps the dead code compiling.
//
// Grounding evidence: the listener stubs' own method sets define each
// interface's contract (BannerListener → INPBannerListener etc.).
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

	public class NXPToyLoginResult : NXPToyResult
	{
	}

	public class NXPToyCountryResult : NXPToyResult
	{
	}

	public class NXPToyUserInfoResult : NXPToyResult
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
	// [RE] `using NPA.Auth;` in listener glue — namespace exists, types unused
	// by the rebuild's auth path.
}
