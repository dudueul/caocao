using System;
using System.Collections.Generic;
using UnityEngine;

public class TreasureCreateTabUI : MonoBehaviour
{
	private enum TabType
	{
		None = -1,
		Create = 0,
		Passive = 1
	}

	private enum RecipeTabType
	{
		All = 0,
		Owned = 1
	}

	private const int STONE_ITEM_ID = 3100269;

	private TabType tabType;

	private RecipeTabType recipeTabType;

	private CraftTreasure selectedRecipe;

	private TreasureRecipeListItem.CellData selectedCellData;

	private UserItem selectedBaseItem;

	private UserItem selectedStuffItem;

	private TreasureBookCellData prevSelectedStuffCellData;

	private List<int> recipeItemIds;

	private List<UserItem> temp;

	private List<CraftTreasure> ownedRecipes;

	private List<CraftTreasure> allRecipes;

	private List<UserItem> stuffItems;

	private bool progressingSelectPassive;

	private CaocaoTabButtons m_tabButton;

	private UILabelLocalized m_labelDesc;

	private Transform m_trsCreate1;

	private Transform m_trsCreate2;

	private UILabelLocalized m_labelGridTitle;

	private CaocaoTabButtons m_tabButtonRecipe;

	private UIReuseGrid m_gridRecipe;

	private TooltipContentsGenerator m_tooltipRecipe;

	private UILabelLocalized m_labelSelectRecipeBtn;

	private UIReuseGrid m_gridStuff;

	private UILabelLocalized m_labelConfirm;

	private UILabelLocalized m_labelCost;

	private GameObject m_btnBack;

	private GameObject m_btnPassiveList;

	private Transform m_trsCreateDetail;

	private Transform m_trsPassiveDetail;

	public ulong selectedPassiveItemSrl
	{
		get
		{
			return 0uL;
		}
	}

	public bool initiated { get; private set; }

	private CaocaoTabButtons tabButton
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelDesc
	{
		get
		{
			return null;
		}
	}

	private Transform trsCreate1
	{
		get
		{
			return null;
		}
	}

	private Transform trsCreate2
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelGridTitle
	{
		get
		{
			return null;
		}
	}

	private CaocaoTabButtons tabButtonRecipe
	{
		get
		{
			return null;
		}
	}

	private UIReuseGrid gridRecipe
	{
		get
		{
			return null;
		}
	}

	private TooltipContentsGenerator tooltipRecipe
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelSelectRecipeBtn
	{
		get
		{
			return null;
		}
	}

	private UIReuseGrid gridStuff
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelConfirm
	{
		get
		{
			return null;
		}
	}

	private UILabelLocalized labelCost
	{
		get
		{
			return null;
		}
	}

	private GameObject btnBack
	{
		get
		{
			return null;
		}
	}

	private GameObject btnPassiveList
	{
		get
		{
			return null;
		}
	}

	private Transform trsCreateDetail
	{
		get
		{
			return null;
		}
	}

	private Transform trsPassiveDetail
	{
		get
		{
			return null;
		}
	}

	public void InitUI()
	{
	}

	private void OnEnable()
	{
	}

	private void OnDisable()
	{
	}

	private void OnTabChanged(CaocaoButton btn)
	{
	}

	private void OnRecipeTabChanged(CaocaoButton btn)
	{
	}

	private void UpdateTabButtonStatus()
	{
	}

	private void UpdateLeftUI()
	{
	}

	private void UpdateRecipeListUI()
	{
	}

	private void SortStuffItemList(bool descEnhance)
	{
	}

	private void CheckUsableStuffItem(UserItem userItem, UserItem prevItem, int checkType, Action callback)
	{
	}

	private void CheckEquippedItem(UserItem userItem, Action callback)
	{
	}

	private void CheckEnhancedItem(UserItem userItem, int checkType, Action callback)
	{
	}

	private void UpdateStuffListUI()
	{
	}

	private void UpdateHeroOwnedItemListUI()
	{
	}

	private void OnSelectHeroOwnedStuffItemData(TreasureBookCellData data)
	{
	}

	private void UpdateDesc()
	{
	}

	private void UpdateRightUI()
	{
	}

	private void UpdateCurrentPassiveUI()
	{
	}

	private void CheckIncompleteSelectPassive()
	{
	}

	private void UpdateItemUI(Transform trsInstItem, Transform trsName, UserItem userItem, int itemId, bool registerTooltip, string emptyText = "")
	{
	}

	private void UpdatePassiveUI(Transform trsInstPsv, Transform trsName, int passiveListId, string emptyText = "")
	{
	}

	public void UpdateUI()
	{
	}

	public void OnSelectRecipeButton()
	{
	}

	public void OnConfirmButton()
	{
	}

	private void TryCraftTreasure()
	{
	}

	private void StartCraftTreasure()
	{
	}

	private void TryChangeSocketPassive()
	{
	}

	private HttpRequest.HttpRequestContext CreateChangeTreasureSocketPassiveRequest(int count)
	{
		return null;
	}

	private bool CheckStoneAmount(int needStoneNum)
	{
		return false;
	}

	private void Reset()
	{
	}

	public void OnBackRecipeButton()
	{
	}

	public void OnPassiveListButton()
	{
	}

	private void OnDestroy()
	{
	}
}
