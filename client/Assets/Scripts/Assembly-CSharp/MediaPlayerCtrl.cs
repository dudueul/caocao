using System;
using System.Collections;
using System.Diagnostics;
using System.Runtime.InteropServices;
using UnityEngine;
using UnityEngine.Events;

public class MediaPlayerCtrl : MonoBehaviour
{
	public delegate void VideoEnd();

	public delegate void VideoReady();

	public delegate void VideoError(MEDIAPLAYER_ERROR errorCode, MEDIAPLAYER_ERROR errorCodeExtra);

	public delegate void VideoFirstFrameReady();

	public delegate void VideoResize();

	public delegate void VideoBuffering();

	public delegate void VideoBufferingEnd();

	public enum MEDIAPLAYER_ERROR
	{
		MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK = 200,
		MEDIA_ERROR_IO = -1004,
		MEDIA_ERROR_MALFORMED = -1007,
		MEDIA_ERROR_TIMED_OUT = -110,
		MEDIA_ERROR_UNSUPPORTED = -1010,
		MEDIA_ERROR_SERVER_DIED = 100,
		MEDIA_ERROR_UNKNOWN = 1
	}

	public enum MEDIAPLAYER_STATE
	{
		NOT_READY = 0,
		READY = 1,
		END = 2,
		PLAYING = 3,
		PAUSED = 4,
		STOPPED = 5,
		ERROR = 6
	}

	public enum MEDIA_SCALE
	{
		SCALE_X_TO_Y = 0,
		SCALE_X_TO_Z = 1,
		SCALE_Y_TO_X = 2,
		SCALE_Y_TO_Z = 3,
		SCALE_Z_TO_X = 4,
		SCALE_Z_TO_Y = 5,
		SCALE_X_TO_Y_2 = 6
	}

	private const int BUFFER_SIZE = 256;

	public string m_strFileName;

	public GameObject[] m_TargetMaterial;

	private Texture2D m_VideoTexture;

	private Texture2D m_VideoTextureDummy;

	private Texture2D m_VideoTextureY;

	private Texture2D m_VideoTextureU;

	private Texture2D m_VideoTextureV;

	private Texture2D m_VideoTextureDummyY;

	private Texture2D m_VideoTextureDummyU;

	private Texture2D m_VideoTextureDummyV;

	private MEDIAPLAYER_STATE m_CurrentState;

	private int m_iCurrentSeekPosition;

	private float m_fVolume;

	private int m_iWidth;

	private int m_iHeight;

	private float m_fSpeed;

	public bool m_bFullScreen;

	public bool m_bSupportRockchip;

	public bool m_bPC_FastMode;

	public VideoResize OnResize;

	public VideoReady OnReady;

	public VideoEnd OnEnd;

	public VideoError OnVideoError;

	public VideoFirstFrameReady OnVideoFirstFrameReady;

	public VideoBuffering OnVideoBuffering;

	public VideoBuffering OnVideoBufferingEnd;

	private IntPtr m_texPtr;

	private IntPtr m_texPtrY;

	private IntPtr m_texPtrU;

	private IntPtr m_texPtrV;

	public Shader m_shaderYUV;

	private int m_iPauseFrame;

	private bool m_bBuffring;

	private int m_iAndroidMgrID;

	private bool m_bIsFirstFrameReady;

	private bool m_bFirst;

	public MEDIA_SCALE m_ScaleValue;

	public GameObject[] m_objResize;

	public bool m_bLoop;

	public bool m_bAutoPlay;

	public UnityEvent m_eventOnResize;

	public UnityEvent m_eventOnReady;

	public UnityEvent m_eventOnEnd;

	public UnityEvent m_eventOnVideoError;

	public UnityEvent m_eventOnVideoFirstFrameReady;

	public UnityEvent m_eventOnVideoBuffering;

	public UnityEvent m_eventOnVideoBufferingEnd;

	private bool m_bInit;

	private bool m_bCheckFBO;

	private bool m_bPause;

	private bool m_bPlaying;

	private bool m_bReadyPlay;

	private AndroidJavaObject javaObj;

	static MediaPlayerCtrl()
	{
	}

	[PreserveSig]
	private static extern void InitNDK();

	[PreserveSig]
	private static extern IntPtr EasyMovieTextureRender();

	private void Awake()
	{
	}

	private void Start()
	{
	}

	private void InitPlayer()
	{
	}

	private void OnApplicationQuit()
	{
	}

	private void OnDisable()
	{
	}

	private void OnEnable()
	{
	}

	private void Update()
	{
	}

	public void DeleteVideoTexture()
	{
	}

	public void ResizeTexture()
	{
	}

	public void Resize()
	{
	}

	private void OnError(MEDIAPLAYER_ERROR iCode, MEDIAPLAYER_ERROR iCodeExtra)
	{
	}

	private void OnDestroy()
	{
	}

	private void OnApplicationPause(bool bPause)
	{
	}

	public MEDIAPLAYER_STATE GetCurrentState()
	{
		return MEDIAPLAYER_STATE.NOT_READY;
	}

	public Texture2D GetVideoTexture()
	{
		return null;
	}

	public void Play()
	{
	}

	public void Stop()
	{
	}

	public void Pause()
	{
	}

	public void Load(string strFileName)
	{
	}

	public void SetVolume(float fVolume)
	{
	}

	public int GetSeekPosition()
	{
		return 0;
	}

	public void SeekTo(int iSeek)
	{
	}

	public void SetSpeed(float fSpeed)
	{
	}

	public int GetDuration()
	{
		return 0;
	}

	public float GetSeekBarValue()
	{
		return 0f;
	}

	public void SetSeekBarValue(float fValue)
	{
	}

	public int GetCurrentSeekPercent()
	{
		return 0;
	}

	public int GetVideoWidth()
	{
		return 0;
	}

	public int GetVideoHeight()
	{
		return 0;
	}

	public void UnLoad()
	{
	}

	public void SelectSoundTrack(int iIndex)
	{
	}

	public int[] GetSoundTrack()
	{
		return null;
	}

	private AndroidJavaObject GetJavaObject()
	{
		return null;
	}

	private void Call_Destroy()
	{
	}

	private void Call_UnLoad()
	{
	}

	private bool Call_Load(string strFileName, int iSeek)
	{
		return false;
	}

	private void Call_UpdateVideoTexture()
	{
	}

	private void Call_SetVolume(float fVolume)
	{
	}

	private void Call_SetSeekPosition(int iSeek)
	{
	}

	private int Call_GetSeekPosition()
	{
		return 0;
	}

	private void Call_Play(int iSeek)
	{
	}

	private void Call_Reset()
	{
	}

	private void Call_Stop()
	{
	}

	private void Call_RePlay()
	{
	}

	private void Call_Pause()
	{
	}

	private int Call_InitNDK()
	{
		return 0;
	}

	private int Call_GetVideoWidth()
	{
		return 0;
	}

	private int Call_GetVideoHeight()
	{
		return 0;
	}

	private bool Call_IsUpdateFrame()
	{
		return false;
	}

	private void Call_SetUnityTexture(int iTextureID)
	{
	}

	private void Call_SetWindowSize()
	{
	}

	private void Call_SetLooping(bool bLoop)
	{
	}

	private void Call_SetRockchip(bool bValue)
	{
	}

	private int Call_GetDuration()
	{
		return 0;
	}

	private int Call_GetCurrentSeekPercent()
	{
		return 0;
	}

	private int Call_GetError()
	{
		return 0;
	}

	private void Call_SetSplitOBB(bool bValue, string strOBBName)
	{
	}

	private int Call_GetErrorExtra()
	{
		return 0;
	}

	private void Call_SetUnityActivity()
	{
	}

	private void Call_SetNotReady()
	{
	}

	private void Call_InitJniManager()
	{
	}

	private void Call_SetSpeed(float fSpeed)
	{
	}

	private MEDIAPLAYER_STATE Call_GetStatus()
	{
		return MEDIAPLAYER_STATE.NOT_READY;
	}

	private void Call_SelectSoundTrack(int iIndex)
	{
	}

	private int[] Call_GetSoundTrack()
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator DownloadStreamingVideoAndLoad(string strURL)
	{
		return null;
	}

	[DebuggerHidden]
	public IEnumerator DownloadStreamingVideoAndLoad2(string strURL)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator CopyStreamingAssetVideoAndLoad(string strURL)
	{
		return null;
	}
}
