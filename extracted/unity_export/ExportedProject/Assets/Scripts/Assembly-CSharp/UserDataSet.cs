using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine.SceneManagement;

public class UserDataSet : iTransmitContainer
{
	private MyUserData m_myUserData;

	private OpponentUserData m_opponentUserData;

	private Dictionary<ulong, IUserDataBase> m_uid2UserData;

	private Dictionary<ulong, UserInfo> m_userInfos;

	public static UserDataSet instance
	{
		get
		{
			return null;
		}
	}

	public Dictionary<ulong, IUserDataBase> Set
	{
		get
		{
			return null;
		}
	}

	// C# has no syntax for parameterized property 'Item'.
	public IUserDataBase get_Item(ulong uid)
	{
		return null;
	}

	public MyUserData GetMyUserData()
	{
		return null;
	}

	public bool HasMyUserData()
	{
		return false;
	}

	public void DestroyMyUserData()
	{
	}

	public OpponentUserData GetOpponentUserData()
	{
		return null;
	}

	public void DestroyOpponentUserData()
	{
	}

	public NPCData GetNpcData(ulong uid)
	{
		return null;
	}

	public ArenaOwnData GetArenaOwnData()
	{
		return null;
	}

	public void Clear()
	{
	}

	public IUserDataBase GetUserData(ulong uid)
	{
		return null;
	}

	public void Register(IUserDataBase userData)
	{
	}

	public void Unregister(IUserDataBase userData)
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

	public UserInfo RegisterUserInfo(ulong uid)
	{
		return null;
	}

	[DebuggerHidden]
	private IEnumerator WaitForUserInfo(ulong uid)
	{
		return null;
	}

	public void GetUserInfoAsync(ulong uid, Action<UserInfo> callback)
	{
	}

	[DebuggerHidden]
	private IEnumerator GetUserInfoCoroutine(ulong uid, Action<UserInfo> callback)
	{
		return null;
	}

	private void RequestUserInfo(ulong uid)
	{
	}

	private void OnGetUserInfo(JsonData response)
	{
	}
}
