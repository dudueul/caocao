using UnityEngine;

namespace MAssist
{
	public static class MAssistSkinManager
	{
		public static float targetDPI;

		public static float ModifyedSceneWidth;

		public static float ModifyedSceneHeight;

		public static Color darkWhiteColor;

		public static Color deepDarkWhiteColor;

		public static Color lightstGrayColor;

		public static Color brightGrayColor;

		public static Color lightGrayColor;

		public static Color grayColor;

		public static Color darkGrayColor;

		public static Color deepGrayColor;

		public static Color DataBoxColor;

		public static Color ButtonColor;

		public static Color SelectedGreenColor;

		public static Color SelectedGreenBoxColor;

		public static Color SelectedInstanceWindowColor;

		public static Color AlretButtonColor;

		public static Color emphasisColor;

		public static Color infoColor;

		public static Color warnColor;

		public static Color errorColor;

		public static Color SkyColor;

		public static GUIStyle Box;

		public static GUIStyle Button;

		public static GUIStyle NoneBox;

		public static GUIStyle NoneBoxLabel;

		public static GUIStyle ZeroMarginBox;

		public static GUIStyle ZeroMarginBoxLabel;

		public static GUIStyle ZeroMarginButton;

		public static GUIStyle LogLevelStyle;

		public static GUIStyle LogLabelStyle;

		public static GUIStyle LogLabelGrayStyle;

		public static GUIStyle StackFrameBoxStyle;

		public static GUIStyle MethodTagStyle;

		public static GUIStyle LogDataBoxStyle;

		public static GUIStyle OutlineToggle;

		public static GUIStyle NameLabel;

		public static GUIStyle RoundBoxLeftStyle;

		public static GUIStyle RoundBoxMiddleStyle;

		public static GUIStyle RoundBoxRightStyle;

		public static GUIStyle RightAlignLabelStyle;

		public static GUIStyle RightAlignBoxStyle;

		public static GUIStyle MiddleCenterAlignBoxStyle;

		public static GUIStyle RightAlignButtonStyle;

		public static Texture2D dot;

		public static Texture2D publicFieldIcon;

		public static Texture2D privateFieldIcon;

		public static Texture2D propertyIcon;

		public static Texture2D penIcon;

		public static Texture2D watchIcon;

		public static Texture2D downArrow;

		public static Texture2D leftArrow;

		public static Texture2D windowOnTex;

		public static Texture2D windowOffTex;

		public static Texture2D gameObjectOnTex;

		public static Texture2D gameObjectOffTex;

		public static Texture2D extendArrowOpen;

		public static Texture2D extendArrowClose;

		private static GUISkin _logViewerSkin;

		public static GUISkin logViewerSkin
		{
			get
			{
				return null;
			}
		}

		public static void SetDPI()
		{
		}

		public static float GetButtonHeight(bool isEditorMode)
		{
			return 0f;
		}

		public static void Reload()
		{
		}
	}
}
