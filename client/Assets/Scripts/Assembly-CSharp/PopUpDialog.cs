using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using System.Runtime.InteropServices;
using UnityEngine;

public class PopUpDialog : MonoBehaviour
{
	[StructLayout((LayoutKind)0, Size = 8)]
	public struct ColorCode
	{
		public int index;

		public int length;
	}

	public UILabelLocalized m_LabelName;

	public GameObject m_WaitingIcon;

	private float m_intervalForOneCharacter;

	private float m_fadeTime;

	private float m_waitingIconDelay;

	private GameObject m_emoticon;

	private float m_lastTypingTime;

	private bool m_closing;

	private bool m_complete;

	private bool m_fadeout;

	private bool m_waitingClick;

	private Coroutine m_closeCoroutine;

	private bool m_needClickToClose;

	public bool manualClose;

	private string m_fullMessage;

	private List<ColorCode> m_colorCodes;

	private int m_printedLength;

	public UITexture m_unitFaceTexure;

	private AudioSource m_textSound;

	private Vector3? m_localPos;

	public Action OnUpdateOnce;

	public Action OnClose;

	private UIPanel m_uiPanel;

	private UILabelLocalized m_LabelMessage;

	public UIPanel uiPanel
	{
		get
		{
			return null;
		}
	}

	public bool waitingClick
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private static PopUpDialog CreateInstance(EnumData.DialogPortraitPosition side, Vector3 pos)
	{
		return null;
	}

	public static PopUpDialog CreateInstance(EnumData.DialogType dialogType, EnumData.DialogPortraitPosition direct, bool isIllust, int unitID, int faceId, int faceType, string nick, bool needClickToClose, bool camFocus2Unit, UnitData explicitFocusUnit, bool showEmoticon, string message, params object[] args)
	{
		return null;
	}

	public static PopUpDialog CreateInstance(EnumData.DialogType dialogType, EnumData.DialogPortraitPosition direct, string IllustName, int unitID, string showNickname, int faceId, int faceType, string nick, bool needClickToClose, bool camFocus2Unit, UnitData explicitFocusUnit, bool showEmoticon, string message, params object[] args)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowDialogBox(EnumData.DialogType dialogType, Unit pd, int faceId, UnitData unitData, int faceType, bool needClickToClose, bool showEmoticon, string message, params object[] args)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator ShowDialogBox(EnumData.DialogType dialogType, Unit pd, string faceName, int faceId, int faceType, UnitData unitData, string showNickname, bool needClickToClose, bool showEmoticon, string message, params object[] args)
	{
		return null;
	}

	public void SetMessage(int faceId, string name, string nameAlias, string message, bool ally, int faceType, EnumData.DialogType dialogType, bool needClickToClose)
	{
	}

	public void Init()
	{
	}

	private void Update()
	{
	}

	private void LateUpdate()
	{
	}

	public bool IsCompleted()
	{
		return false;
	}

	public bool IsTypingCompleted()
	{
		return false;
	}

	private void ShowTalkEmoticon(UnitData unit, int unitId, EnumData.DialogType dialogType)
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnDestroy()
	{
	}

	private void OnClick()
	{
	}

	public void Close(float interval)
	{
	}

	[DebuggerHidden]
	private IEnumerator CloseCoroutine(float interval)
	{
		return null;
	}

	public void Show(float fadeTime)
	{
	}

	[DebuggerHidden]
	public IEnumerator Hide(float interval)
	{
		return null;
	}

	public void CompleteTyping(float waitingIconDelay)
	{
	}

	[DebuggerHidden]
	private IEnumerator ShowWaitingIcon(float interval)
	{
		return null;
	}

	private void StartTyping(string message)
	{
	}

	private void DoTyping(int length)
	{
	}

	private void SetUnitImage(int faceID, string sName, string nameAlias, int faceType)
	{
	}

	private void SetUnitFaceTexture(int faceID, int faceType)
	{
	}

	private void SetUnitFaceTexture(string faceName)
	{
	}

	private void SetUnitName(string sName, string nameAlias)
	{
	}

	private void SetBg(EnumData.DialogType dialogType)
	{
	}

	private void SetNameColor(bool isAlly)
	{
	}

	private static string FormatMessage(string message, params object[] args)
	{
		return null;
	}

	private void ChangeLetterTypingMode(bool isAtOneTime)
	{
	}
}
