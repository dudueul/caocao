using System;
using UnityEngine;

public class RewardItemUI : MonoBehaviour
{
	public enum FrameType
	{
		none = 0,
		silver = 1,
		gold = 2
	}

	[SerializeField]
	private Transform rewardParent;

	[SerializeField]
	private UISprite frame;

	[SerializeField]
	private GameObject dim;

	[SerializeField]
	private Vector3 localPosition;

	[SerializeField]
	private Vector3 scale;

	[SerializeField]
	private int depth;

	[SerializeField]
	private UILabelLocalized desc;

	[SerializeField]
	private Color selectDesc;

	[SerializeField]
	private GameObject select;

	public bool isDim
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool selected
	{
		get
		{
			return false;
		}
		set
		{
		}
	}

	public bool showDesc
	{
		set
		{
		}
	}

	public void SetFrame(FrameType type)
	{
	}

	public void SetDesc(string key, params object[] args)
	{
	}

	public void SetDesc(string key)
	{
	}

	public void SetRewardInfo(RewardInfo rewardInfo)
	{
	}

	public void SetRewardInfo(RewardInfo rewardInfo, Action<RewardItemUI> onClickHandler)
	{
	}

	public void ShowTooltip()
	{
	}

	private PrefabInstance GetPrefabInstance(Transform parent, GameObject prefab, string instName, Vector3 instLocalPos, Vector3 instScale, int instDepth)
	{
		return null;
	}

	private void ActivePrefabInstance(Transform parent, string instName)
	{
	}

	private void UpdateRewardGood(Transform parent, RewardInfo reward, Vector3 instLocalPos, Vector3 instScale, int instDepth, Action<GoodUI> showTooltip)
	{
	}

	private void UpdateRewardItem(Transform parent, RewardInfo reward, Vector3 instLocalPos, Vector3 instScale, int instDepth, Action<ItemUI> showTooltip)
	{
	}

	private void UpdateRewardRune(Transform parent, RewardInfo reward, Vector3 instLocalPos, Vector3 instScale, int instDepth, Action<RuneUI> showTooltip)
	{
	}

	private UIButton GetIconFrameButton(Transform inst)
	{
		return null;
	}
}
