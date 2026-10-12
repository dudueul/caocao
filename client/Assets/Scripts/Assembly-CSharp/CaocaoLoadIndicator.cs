using System;
using System.Collections;
using System.Collections.Generic;
using System.Diagnostics;
using LitJson;
using UnityEngine.SceneManagement;

public class CaocaoLoadIndicator : Singleton<CaocaoLoadIndicator>
{
	public enum LoadingType
	{
		Event = 0,
		Product = 1,
		None = 2
	}

	public class CustomLoading : ITransmittable, IUpsertedMarkable
	{
		private ulong m_srl;

		private string m_imagePath;

		private string m_fileName;

		private LoadingType m_loadingType;

		private DateTime m_startDate;

		private DateTime m_endDate;

		private int m_minRank;

		private int m_maxRank;

		private string m_url;

		private string m_cachedPath;

		public bool isAdded { get; set; }

		public bool isUpdated { get; set; }

		public string imagePath
		{
			get
			{
				return null;
			}
		}

		public string fileName
		{
			get
			{
				return null;
			}
		}

		public string url
		{
			get
			{
				return null;
			}
		}

		public string cachedPath
		{
			get
			{
				return null;
			}
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}

		public bool ValidLoadingType(LoadingType loadingType)
		{
			return false;
		}

		public bool ValidDate(DateTime now)
		{
			return false;
		}

		public bool ValidRank(int rank)
		{
			return false;
		}

		public bool IsExpired(DateTime now)
		{
			return false;
		}
	}

	public class CustomLoadings : ITransmittable, iTransmitContainer
	{
		private Dictionary<ulong, CustomLoading> m_srl2Values;

		private List<ulong> m_srls;

		private bool m_allDownloaded;

		// C# has no syntax for parameterized property 'Item'.
		public CustomLoading get_Item(ulong srl)
		{
			return null;
		}

		public List<ulong> srls
		{
			get
			{
				return null;
			}
		}

		protected override void DeserializeImpl(JsonData jsonData)
		{
		}

		private bool CheckDeleted()
		{
			return false;
		}

		private void CheckAdded()
		{
		}

		private void DeleteUnusedFiles()
		{
		}

		public string GetRandomLoadingCachedPath(LoadingType loadingType)
		{
			return null;
		}

		public void CheckUndownloaded()
		{
		}
	}

	private const string LoadingSceneName = "Loading";

	public static string ActiveSceneName;

	private bool isLoadingSceneLoaded;

	private bool autoDestroy;

	private bool showLoadingScene;

	private bool didFadeInLoadingScene;

	private bool isDoneLoading;

	public Action OnTransitionStart;

	public Action OnTransitionEnded;

	public Action OnceTransitionEnded;

	private bool m_isLoading;

	private CustomLoadings m_customLoadings;

	public bool isLoading
	{
		get
		{
			return false;
		}
		private set
		{
		}
	}

	public void Deserialize(JsonData jsonData)
	{
	}

	private void initFlgas()
	{
	}

	public void LoadScene(string sceneName, bool autoDestroy = true, bool showLoadingScene = true)
	{
	}

	[DebuggerHidden]
	private IEnumerator _LoadScene(string sceneName)
	{
		return null;
	}

	public void FadeInLoadingScene()
	{
	}

	[DebuggerHidden]
	private IEnumerator _FadeInLoadingScene()
	{
		return null;
	}

	public void DestroyLoadingUI(Action callback)
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
}
