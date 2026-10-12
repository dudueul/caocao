using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using UnityEngine;
using UnityEngine.SceneManagement;
using antilunchbox;

public class SoundManager : antilunchbox.Singleton<SoundManager>
{
	public enum PlayMethod
	{
		ContinuousPlayThrough = 0,
		ContinuousPlayThroughWithDelay = 1,
		ContinuousPlayThroughWithRandomDelayInRange = 2,
		OncePlayThrough = 3,
		OncePlayThroughWithDelay = 4,
		OncePlayThroughWithRandomDelayInRange = 5,
		ShufflePlayThrough = 6,
		ShufflePlayThroughWithDelay = 7,
		ShufflePlayThroughWithRandomDelayInRange = 8
	}

	public delegate void SongCallBack();

	public const string VIEW = "view";

	public const string EDIT = "edit";

	public const string HIDE = "hide";

	private bool _viewAll;

	[SerializeField]
	public Hashtable songStatus;

	public bool helpOn;

	public bool showInfo;

	public bool showDev;

	public bool showList;

	public bool showAdd;

	public bool showSFX;

	public List<bool> showSFXDetails;

	public int groupAddIndex;

	public int autoPrepoolAmount;

	public float autoBaseVolume;

	public float autoVolumeVariation;

	public float autoPitchVariation;

	public bool showAsGrouped;

	public List<SoundConnection> soundConnections;

	[SerializeField]
	public AudioSource[] audios;

	private bool[] audiosPaused;

	public string currentLevel;

	public SoundConnection currentSoundConnection;

	private AudioSource currentSource;

	public float crossDuration;

	private float modifiedCrossDuration;

	public bool showDebug;

	public bool offTheBGM;

	private bool ignoreCrossDuration;

	private int currentPlaying;

	private bool silentLevel;

	public bool isPaused;

	private bool skipSongs;

	private int skipAmount;

	private bool[] inCrossing;

	private bool[] outCrossing;

	public bool movingOnFromSong;

	private float lastLevelLoad;

	public const int SOUNDMANAGER_FALSE = -1;

	public SongCallBack OnSongEnd;

	public SongCallBack OnSongBegin;

	public SongCallBack OnCrossOutBegin;

	public SongCallBack OnCrossInBegin;

	private SongCallBack InternalCallback;

	private int currentSongIndex;

	private bool ignoreFromLosingFocus;

	public bool ignoreLevelLoad;

	private float _maxMusicVolume;

	private float _maxVolume;

	private bool _mutedMusic;

	public string resourcesPath;

	public List<AudioClip> storedSFXs;

	public List<GameObject> unOwnedSFXObjects;

	public Dictionary<int, string> cappedSFXObjects;

	public Dictionary<AudioSource, float> delayedAudioSources;

	public Dictionary<AudioSource, SongCallBack> runOnEndFunctions;

	private AudioSource duckSource;

	private SongCallBack duckFunction;

	private bool isDucking;

	private int duckNumber;

	private float preDuckVolume;

	private float preDuckVolumeMusic;

	private float preDuckVolumeSFX;

	private float preDuckPitch;

	private float preDuckPitchMusic;

	private float preDuckPitchSFX;

	public static float duckStartSpeed;

	public static float duckEndSpeed;

	public List<SFXGroup> sfxGroups;

	public List<string> clipToGroupKeys;

	public List<string> clipToGroupValues;

	private Dictionary<string, SFXGroup> groups;

	private Dictionary<string, string> clipsInGroups;

	private Dictionary<string, AudioClip> allClips;

	private Dictionary<string, int> prepools;

	private Dictionary<string, float> baseVolumes;

	private Dictionary<string, float> volumeVariations;

	private Dictionary<string, float> pitchVariations;

	public bool offTheSFX;

	public int capAmount;

	private float _volumeSFX;

	private float _pitchSFX;

	private float _maxSFXVolume;

	private bool _mutedSFX;

	private Dictionary<AudioClip, SFXPoolInfo> ownedPools;

	public List<int> sfxPrePoolAmounts;

	public List<float> sfxBaseVolumes;

	public List<float> sfxVolumeVariations;

	public List<float> sfxPitchVariations;

	public float SFXObjectLifetime;

	public List<string> currentPockets;

	public bool viewAll
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public static SoundManager Instance
	{
		get
		{
			return null;
		}
		set
		{
		}
	}

	public float volume1
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float volume2
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float maxMusicVolume
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float maxVolume
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public bool mutedMusic
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool muted
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private bool crossingIn
	{
		get
		{
			return false;
		}
	}

	private bool crossingOut
	{
		get
		{
			return false;
		}
	}

	public float volumeSFX
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float pitchSFX
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public float maxSFXVolume
	{
		get
		{
			return 0f;
		}
		set
		{
		}
	}

	public bool mutedSFX
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	private void Update()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnLevelFinishedLoading(Scene scene, LoadSceneMode mode)
	{
	}

	private void OnApplicationPause(bool pause)
	{
	}

	private void CommonHandleApplicationFocus(bool applicationFocus)
	{
	}

	public static void AddSoundConnection(SoundConnection sc)
	{
	}

	public static void RemoveSoundConnectionForLevel(string lvl)
	{
	}

	public static void ReplaceSoundConnection(SoundConnection sc)
	{
	}

	public static int SoundConnectionsContainsThisLevel(string lvl)
	{
		return 0;
	}

	public static SoundConnection GetSoundConnectionForThisLevel(string lvl)
	{
		return null;
	}

	public static bool MuteMusic()
	{
		return false;
	}

	public static bool MuteMusic(bool toggle)
	{
		return false;
	}

	public static bool IsMusicMuted()
	{
		return false;
	}

	public static bool Mute()
	{
		return false;
	}

	public static bool Mute(bool toggle)
	{
		return false;
	}

	public static bool IsMuted()
	{
		return false;
	}

	public static void SetVolumeMusic(float setVolume)
	{
	}

	public static float GetVolumeMusic()
	{
		return 0f;
	}

	public static void SetPitchMusic(float setPitch)
	{
	}

	public static float GetPitchMusic()
	{
		return 0f;
	}

	public static void SetVolume(float setVolume)
	{
	}

	public static float GetVolume()
	{
		return 0f;
	}

	public static void SetPitch(float setPitch)
	{
	}

	public static float GetPitch()
	{
		return 0f;
	}

	public static float GetCrossDuration()
	{
		return 0f;
	}

	public static void SetCrossDuration(float duration)
	{
	}

	public static string GetDefaultResourcesPath()
	{
		return null;
	}

	public static void SetDefaultResourcesPath(string path)
	{
	}

	public static SoundConnection GetCurrentSoundConnection()
	{
		return null;
	}

	public static void SetCurrentSoundConnection(SoundConnection connection)
	{
	}

	public static void SetDisableBGM(bool disabled)
	{
	}

	public static void SetDisableSFX(bool disabled)
	{
	}

	public static void PlayConnection(SoundConnection sc, bool syncPlaybackTime = false, int trackNumber = 0)
	{
	}

	public static void PlayConnection(string levelName, bool syncPlaybackTime = false, int trackNumber = 0)
	{
	}

	public static SoundConnection CreateSoundConnection(string lvl, params AudioClip[] audioList)
	{
		return null;
	}

	public static SoundConnection CreateSoundConnection(string lvl, PlayMethod method, params AudioClip[] audioList)
	{
		return null;
	}

	public static SoundConnection CreateSoundConnection(string lvl, PlayMethod method, float delayPlay, params AudioClip[] audioList)
	{
		return null;
	}

	public static SoundConnection CreateSoundConnection(string lvl, PlayMethod method, float minDelayPlay, float maxDelayPlay, params AudioClip[] audioList)
	{
		return null;
	}

	public static void PlayImmediately(AudioClip clip2play, bool loop = false, SongCallBack runOnEndFunction = null)
	{
	}

	public static void Play(AudioClip clip2play, bool loop = false, SongCallBack runOnEndFunction = null)
	{
	}

	public static void StopMusicImmediately()
	{
	}

	public static void StopMusic()
	{
	}

	public static void Stop()
	{
	}

	public static void Pause()
	{
	}

	public static void UnPause()
	{
	}

	public static void PauseToggle()
	{
	}

	public static bool IsPaused()
	{
		return false;
	}

	public static void SetIgnoreLevelLoad(bool ignore)
	{
	}

	public static List<AudioClip> GetCurrentSongList()
	{
		return null;
	}

	public static AudioClip GetCurrentSong()
	{
		return null;
	}

	public static void Next()
	{
	}

	public static void Prev()
	{
	}

	public static bool ClipNameIsValid(string clipName)
	{
		return false;
	}

	public static bool GroupNameIsValid(string groupName)
	{
		return false;
	}

	public static AudioSource GetCurrentAudioSource()
	{
		return null;
	}

	public static int GetTrackNumber(AudioClip clip)
	{
		return 0;
	}

	private void Awake()
	{
	}

	private void Setup()
	{
	}

	private void Init()
	{
	}

	private void ClearAudioSources()
	{
	}

	private void _PlayConnection(SoundConnection sc)
	{
	}

	private void _PlayConnection(string levelName)
	{
	}

	private void StopPreviousPlaySoundConnection()
	{
	}

	private int CheckWhosNotPlaying()
	{
		return 0;
	}

	private int CheckWhosPlaying()
	{
		return 0;
	}

	private void HandleLevel()
	{
	}

	private void _PlayImmediately(AudioClip clip2play, bool loop, SongCallBack runOnEndFunction)
	{
	}

	private void _PlayImmediately(AudioClip clip2play, bool loop)
	{
	}

	private void _PlayImmediately(AudioClip clip2play)
	{
	}

	private void _Play(AudioClip clip2play, bool loop, SongCallBack runOnEndFunction)
	{
	}

	private void _Play(AudioClip clip2play, bool loop)
	{
	}

	private void _Play(AudioClip clip2play)
	{
	}

	private void _StopMusicImmediately()
	{
	}

	private void _StopMusic()
	{
	}

	private void _Pause()
	{
	}

	private void _UnPause()
	{
	}

	private int PlayClip(AudioClip clip2play, float clipVolume = 1f)
	{
		return 0;
	}

	[DebuggerHidden]
	private IEnumerator Crossfade(object[] param)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator Crossout(object[] param)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator CrossoutAll(float duration)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator Crossin(object[] param)
	{
		return null;
	}

	private AudioSource IsPlaying(AudioClip clip)
	{
		return null;
	}

	private AudioSource IsPlaying(AudioClip clip, bool regardlessOfCrossOut)
	{
		return null;
	}

	private bool IsPlaying()
	{
		return false;
	}

	private int GetAudioSourceIndex(AudioSource source)
	{
		return 0;
	}

	private void StopAllNonSoundConnectionCoroutines()
	{
	}

	private int GetActualCurrentPlayingIndex()
	{
		return 0;
	}

	[DebuggerHidden]
	private IEnumerator PlaySoundConnection(SoundConnection sc)
	{
		return null;
	}

	private void SetNextSongInQueue()
	{
	}

	public static void SetSFXCap(int cap)
	{
	}

	public static AudioSource PlaySE(AudioClip clip, bool looping = false, float delay = 0f, float volume = float.MaxValue, float pitch = float.MaxValue)
	{
		return null;
	}

	public static AudioSource PlaySE(AudioSource aS, AudioClip clip, bool looping = false, float delay = 0f, float volume = float.MaxValue, float pitch = float.MaxValue)
	{
		return null;
	}

	public static AudioSource PlayVoice(AudioClip clip, bool looping = false, float delay = 0f, float volume = float.MaxValue, float pitch = float.MaxValue)
	{
		return null;
	}

	public static AudioSource PlayVoice(AudioSource aS, AudioClip clip, bool looping = false, float delay = 0f, float volume = float.MaxValue, float pitch = float.MaxValue)
	{
		return null;
	}

	private static AudioSource SetMuteToPlayOnAwake(AudioSource aS, float volume)
	{
		return null;
	}

	public static AudioSource PlaySFX(AudioClip clip, bool looping = false, float delay = 0f, float volume = float.MaxValue, float pitch = float.MaxValue, Vector3 location = default(Vector3), SongCallBack runOnEndFunction = null, SoundDuckingSetting duckingSetting = SoundDuckingSetting.DoNotDuck, float duckVolume = 0f, float duckPitch = 1f)
	{
		return null;
	}

	private static AudioSource PlaySFX(string clipName, bool looping = false, float delay = 0f, float volume = float.MaxValue, float pitch = float.MaxValue, Vector3 location = default(Vector3), SongCallBack runOnEndFunction = null, SoundDuckingSetting duckingSetting = SoundDuckingSetting.DoNotDuck, float duckVolume = 0f, float duckPitch = 1f)
	{
		return null;
	}

	public static AudioSource PlayCappedSFX(AudioClip clip, string cappedID, float volume = float.MaxValue, float pitch = float.MaxValue, Vector3 location = default(Vector3))
	{
		return null;
	}

	public static AudioSource PlayCappedSFX(string clipName, string cappedID, float volume = float.MaxValue, float pitch = float.MaxValue, Vector3 location = default(Vector3))
	{
		return null;
	}

	public static AudioSource PlayCappedSFX(AudioSource aS, AudioClip clip, string cappedID, float volume = float.MaxValue, float pitch = float.MaxValue)
	{
		return null;
	}

	public static AudioSource PlayCappedSFX(AudioSource aS, string clipName, string cappedID, float volume = float.MaxValue, float pitch = float.MaxValue)
	{
		return null;
	}

	private static AudioSource PlaySFX(AudioSource aS, AudioClip clip, bool looping = false, float delay = 0f, float volume = float.MaxValue, float pitch = float.MaxValue, SongCallBack runOnEndFunction = null, SoundDuckingSetting duckingSetting = SoundDuckingSetting.DoNotDuck, float duckVolume = 0f, float duckPitch = 1f)
	{
		return null;
	}

	private static AudioSource PlaySFX(AudioSource aS, string clipName, bool looping = false, float delay = 0f, float volume = float.MaxValue, float pitch = float.MaxValue, SongCallBack runOnEndFunction = null, SoundDuckingSetting duckingSetting = SoundDuckingSetting.DoNotDuck, float duckVolume = 0f, float duckPitch = 1f)
	{
		return null;
	}

	public static void StopSFXObject(AudioSource aS)
	{
	}

	private static AudioSource PlaySFX(GameObject gO, AudioClip clip, bool looping = false, float delay = 0f, float volume = float.MaxValue, float pitch = float.MaxValue, SongCallBack runOnEndFunction = null, SoundDuckingSetting duckingSetting = SoundDuckingSetting.DoNotDuck, float duckVolume = 0f, float duckPitch = 1f)
	{
		return null;
	}

	private static AudioSource PlaySFX(GameObject gO, string clipName, bool looping = false, float delay = 0f, float volume = float.MaxValue, float pitch = float.MaxValue, SongCallBack runOnEndFunction = null, SoundDuckingSetting duckingSetting = SoundDuckingSetting.DoNotDuck, float duckVolume = 0f, float duckPitch = 1f)
	{
		return null;
	}

	public static void StopSFXObject(GameObject gO)
	{
	}

	public static void StopSFX(AudioClip clip)
	{
	}

	public static void StopSFX()
	{
	}

	public static AudioSource PlaySFXLoop(AudioSource aS, AudioClip clip, bool tillDestroy = true, float volume = float.MaxValue, float pitch = float.MaxValue, float maxDuration = 0f, SongCallBack runOnEndFunction = null, SoundDuckingSetting duckingSetting = SoundDuckingSetting.DoNotDuck, float duckVolume = 0f, float duckPitch = 1f)
	{
		return null;
	}

	public static AudioSource PlaySFXLoop(AudioSource aS, string clipName, bool tillDestroy = true, float volume = float.MaxValue, float pitch = float.MaxValue, float maxDuration = 0f, SongCallBack runOnEndFunction = null, SoundDuckingSetting duckingSetting = SoundDuckingSetting.DoNotDuck, float duckVolume = 0f, float duckPitch = 1f)
	{
		return null;
	}

	public static AudioSource PlaySFXLoop(GameObject gO, AudioClip clip, bool tillDestroy = true, float volume = float.MaxValue, float pitch = float.MaxValue, float maxDuration = 0f, SongCallBack runOnEndFunction = null, SoundDuckingSetting duckingSetting = SoundDuckingSetting.DoNotDuck, float duckVolume = 0f, float duckPitch = 1f)
	{
		return null;
	}

	public static AudioSource PlaySFXLoop(GameObject gO, string clipName, bool tillDestroy = true, float volume = float.MaxValue, float pitch = float.MaxValue, float maxDuration = 0f, SongCallBack runOnEndFunction = null, SoundDuckingSetting duckingSetting = SoundDuckingSetting.DoNotDuck, float duckVolume = 0f, float duckPitch = 1f)
	{
		return null;
	}

	public static bool MuteSFX(bool toggle)
	{
		return false;
	}

	public static bool MuteSFX()
	{
		return false;
	}

	public static bool IsSFXMuted()
	{
		return false;
	}

	public static void SetVolumeSFX(float setVolume)
	{
	}

	public static void SetVolumeSFX(float setVolume, bool ignoreMaxSFXVolume, params AudioSource[] audioSources)
	{
	}

	public static void SetVolumeSFX(float setVolume, bool ignoreMaxSFXVolume, params GameObject[] sfxObjects)
	{
	}

	public static float GetVolumeSFX()
	{
		return 0f;
	}

	public static void SetPitchSFX(float setPitch)
	{
	}

	public static void SetPitchSFX(float setPitch, params AudioSource[] audioSources)
	{
	}

	public static void SetPitchSFX(float setPitch, params GameObject[] sfxObjects)
	{
	}

	public static float GetPitchSFX()
	{
		return 0f;
	}

	public static void SaveSFX(AudioClip clip, string grpName)
	{
	}

	public static void SaveSFX(AudioClip clip, SFXGroup grp)
	{
	}

	public static void SaveSFX(params AudioClip[] clips)
	{
	}

	public static void DeleteSFX()
	{
	}

	public static void DeleteSFX(params AudioClip[] clips)
	{
	}

	public static void DeleteSFX(params string[] clipNames)
	{
	}

	public static void ApplySFXAttributes(AudioClip clip, int prepool, float baseVolume, float volumeVariation, float pitchVariation)
	{
	}

	public static void ApplySFXAttributes(string clipName, int prepool, float baseVolume, float volumeVariation, float pitchVariation)
	{
	}

	public static SFXGroup CreateSFXGroup(string grpName, int capAmount)
	{
		return null;
	}

	public static SFXGroup CreateSFXGroup(string grpName)
	{
		return null;
	}

	public static void MoveToSFXGroup(string clipName, string newGroupName)
	{
	}

	public static void RemoveFromSFXGroup(string clipName)
	{
	}

	public static AudioClip LoadFromGroup(string grpName)
	{
		return null;
	}

	public static AudioClip[] LoadAllFromGroup(string grpName)
	{
		return null;
	}

	public static AudioClip Load(string clipname, string customPath)
	{
		return null;
	}

	public static AudioClip Load(string clipname)
	{
		return null;
	}

	public static void ResetSFXObject(GameObject sfxObj)
	{
	}

	public static void Crossfade(float duration, AudioSource fromSource, AudioSource toSource, SongCallBack runOnEndFunction = null)
	{
	}

	public static void Crossfade(float duration, GameObject fromSFXObject, GameObject toSFXObject, SongCallBack runOnEndFunction = null)
	{
	}

	public static void CrossIn(float duration, AudioSource source, SongCallBack runOnEndFunction = null)
	{
	}

	public static void CrossIn(float duration, GameObject sfxObject, SongCallBack runOnEndFunction = null)
	{
	}

	public static void CrossOut(float duration, AudioSource source, SongCallBack runOnEndFunction = null)
	{
	}

	public static void CrossOut(float duration, GameObject sfxObject, SongCallBack runOnEndFunction = null)
	{
	}

	private void SetupSoundFX()
	{
	}

	public void ClearCaches()
	{
	}

	private void PrePoolClip(AudioClip clip, int prepoolAmount)
	{
	}

	private void RemoveSFXObject(SFXPoolInfo info, int index)
	{
	}

	private void SetupDictionary()
	{
	}

	private void AddClipToGroup(string clipName, string groupName)
	{
	}

	private void SetClipToGroup(string clipName, string groupName)
	{
	}

	private void RemoveClipFromGroup(string clipName)
	{
	}

	private string GetClipToGroup(string clipName)
	{
		return null;
	}

	private void PSFX(bool pause)
	{
	}

	private void HandleSFX()
	{
	}

	private GameObject GetNextInactiveSFXObject(AudioClip clip)
	{
		return null;
	}

	private GameObject AddOwnedSFXObject(AudioClip clip)
	{
		return null;
	}

	private AudioSource PlaySFXAt(AudioClip clip, float volume, float pitch, Vector3 location = default(Vector3), bool capped = false, string cappedID = "", bool looping = false, float delay = 0f, SongCallBack runOnEndFunction = null, SoundDuckingSetting duckingSetting = SoundDuckingSetting.DoNotDuck, float duckVolume = 0f, float duckPitch = 1f)
	{
		return null;
	}

	private AudioSource PlaySFXOn(AudioSource aSource, AudioClip clip, float volume, float pitch, bool capped = false, string cappedID = "", bool looping = false, float delay = 0f, SongCallBack runOnEndFunction = null, SoundDuckingSetting duckingSetting = SoundDuckingSetting.DoNotDuck, float duckVolume = 0f, float duckPitch = 1f)
	{
		return null;
	}

	private AudioSource PlaySFXBase(AudioSource aSource, AudioClip clip, float volume, float pitch, bool capped = false, string cappedID = "", bool looping = false, float delay = 0f, SongCallBack runOnEndFunction = null, SoundDuckingSetting duckingSetting = SoundDuckingSetting.DoNotDuck, float duckVolume = 0f, float duckPitch = 1f)
	{
		return null;
	}

	private AudioSource PlaySFXLoopOn(AudioSource source, AudioClip clip, bool tillDestroy, float volume, float pitch, float maxDuration, SongCallBack runOnEndFunction = null, SoundDuckingSetting duckingSetting = SoundDuckingSetting.DoNotDuck, float duckVolume = 0f, float duckPitch = 1f)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator _PlaySFXLoopTillDestroy(GameObject gO, AudioSource source, bool tillDestroy, float maxDuration)
	{
		return null;
	}

	private void CheckInsertionIntoUnownedSFXObjects(AudioSource aSource)
	{
	}

	private void _StopSFX()
	{
	}

	private void _StopSFX(AudioClip clip)
	{
	}

	private bool ShouldContinueLoop(GameObject gO, bool trackEndTime, float endTime)
	{
		return false;
	}

	private bool IsAtCapacity(string cappedID, string clipName)
	{
		return false;
	}

	private bool IsOwnedSFXObject(AudioSource aSource)
	{
		return false;
	}

	private bool IsOwnedSFXObject(GameObject obj)
	{
		return false;
	}

	private SFXGroup GetGroupForClipName(string clipName)
	{
		return null;
	}

	private SFXGroup GetGroupByGroupName(string grpName)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator XFade(float duration, AudioSource a1, AudioSource a2, SongCallBack runOnEndFunction)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator XOut(float duration, AudioSource a1, SongCallBack runOnEndFunction)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator XIn(float duration, AudioSource a1, SongCallBack runOnEndFunction)
	{
		return null;
	}

	private void Duck(SoundDuckingSetting duckingSetting, float duckVolume, float duckPitch, float unDuckedVolume, float unDuckedPitch, params AudioSource[] exceptions)
	{
	}

	[DebuggerHidden]
	private IEnumerator DuckTheTrack(SoundDuckingSetting duckingSetting, float duckVolume, float duckPitch, float unDuckedVolume, float unDuckedPitch, AudioSource[] exceptions)
	{
		return null;
	}
}
