using System;
using UnityEngine;

[ExecuteInEditMode]
public class UnitUI : MonoBehaviour
{
	private bool m_isScrollable;

	private bool m_isCash;

	private int m_faceId;

	public RewardInfo rewardInfo;

	public Action<UnitUI> OnClickHandler;

	public int UnitId { get; private set; }

	public int SkinItemId { get; private set; }

	public bool isScrollable
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool isCash
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public int faceId
	{
		get
		{
			return 0;
		}
		set
		{
		}
	}

	private GameObject bgCash
	{
		get
		{
			return null;
		}
	}

	private GameObject bg
	{
		get
		{
			return null;
		}
	}

	private GameObject bgUnknown
	{
		get
		{
			return null;
		}
	}

	private GameObject face
	{
		get
		{
			return null;
		}
	}

	public bool IsValid()
	{
		return false;
	}

	public void SetUserUnit(UserUnit unit)
	{
	}

	private void SetUnit(int unitId, int skinItemId)
	{
	}

	public void SetReward(RewardInfo rewardInfo)
	{
	}

	public void SetUnit(int unitId, int skinItemId, bool cashUnit)
	{
	}

	public void SetFace(int faceId, int faceType)
	{
	}

	public void SetUnknown()
	{
	}

	private void UpdateUI(Texture faceTexture)
	{
	}

	public void OnClick()
	{
	}
}
