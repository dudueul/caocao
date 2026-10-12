using System;
using System.Collections.Generic;
using CaoCaoIAB;
using TapjoyUnity;

public class ZZTapjoyPlacement : Singleton<ZZTapjoyPlacement>
{
	private class CaocaoPlacement
	{
		private RequestContentState _state;

		private DateTime? ExpirationTime;

		public float tryCount;

		public TJPlacement placement { get; private set; }

		public RequestContentState state
		{
			get
			{
				return RequestContentState.NotRequested;
			}
			set
			{
			}
		}

		public bool SaveCache { get; set; }

		public bool IsExpired
		{
			get
			{
				return false;
			}
		}

		public bool tryCountOver
		{
			get
			{
				return false;
			}
		}

		public CaocaoPlacement(string placementName)
		{
		}

		public void Touch(DateTime expiration_time)
		{
		}

		public string GetName()
		{
			return null;
		}

		public void ShowContent()
		{
		}

		public void RequestContent(DateTime? expiration_time = null)
		{
		}

		public bool IsContentReady()
		{
			return false;
		}

		public bool IsContentAvailable()
		{
			return false;
		}

		public void RequestPurchaseSucceed(Purchase purchase)
		{
		}

		public bool CanRequestContent()
		{
			return false;
		}

		public bool IsWaitingRequestContent()
		{
			return false;
		}

		public bool IsRequestContentFinished()
		{
			return false;
		}

		public bool IsShowing()
		{
			return false;
		}

		public bool IsShown()
		{
			return false;
		}

		public void Clear()
		{
		}
	}

	private enum RequestContentState
	{
		NotRequested = 0,
		Requesting = 1,
		RequestFinished = 2,
		RequestFailed = 3,
		ContentReady = 4,
		PurchaseSucceed = 5,
		ContentShowing = 6,
		Pending = 7,
		ContentShown = 8
	}

	public bool pending;

	public bool failedEnterLobbyShown;

	private List<CaocaoPlacement> placementQ;

	private Dictionary<string, CaocaoPlacement> placementCache;

	private void RequestPlacementContents(CaocaoPlacement cp, DateTime? expiration_time = null)
	{
	}

	private DateTime GetExpirationTime()
	{
		return default(DateTime);
	}

	public void PreloadPlacementContents(string placementName, bool saveCache)
	{
	}

	public void UnloadPlacmentContents(string placementName)
	{
	}

	private void UpdatePlacementContents(CaocaoPlacement cp)
	{
	}

	protected void Start()
	{
	}

	private void OnContentDismiss(TJPlacement p)
	{
	}

	private void OnContentReady(TJPlacement p)
	{
	}

	private void OnPurchaseRequest(TJPlacement p, TJActionRequest ar, string pid)
	{
	}

	private void OnRequestFailure(TJPlacement p, string err)
	{
	}

	private void OnRequestSuccess(TJPlacement p)
	{
	}

	protected override void OnDestroy()
	{
	}

	public void ShowPlacementContent(string placementName, bool addFirst = false)
	{
	}

	private void Update()
	{
	}

	public bool CheckContentReady(string placementName = null)
	{
		return false;
	}
}
