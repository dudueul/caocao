using System;
using System.Collections.Generic;
using Caocao;

public class UserSpellRule : SceneSingleton<UserSpellRule>
{
	public Action updateWalletCallBack;

	public UserSpellWallet ownWallet;

	public UserSpellWallet enemyWallet;

	private List<BattleMapObject> tacticsObject;

	private void Awake()
	{
	}

	public void PayOwn(int pay)
	{
	}

	public void PayEnemy(int pay)
	{
	}

	public void PayTurnCompleted()
	{
	}

	public void BuyOwn(int price)
	{
	}

	public void BuyEnemy(int price)
	{
	}

	public int GetOwnWalletCash()
	{
		return 0;
	}

	public int GetEnemyWalletCash()
	{
		return 0;
	}

	public bool CanOwnBuySpell(int userSpellPrice)
	{
		return false;
	}

	public bool CanEnemyBuySpell(int userSpellPrice)
	{
		return false;
	}

	public int GetPayReward(UnitSide unitSide)
	{
		return 0;
	}

	private void InitWallet()
	{
	}

	private void PayReward()
	{
	}

	private void CallBackUpdateWallet()
	{
	}

	private void ChangePayObjOwner()
	{
	}

	public void CreateTacticsObject(List<int> mapObjectIds, List<IntVector2> positions)
	{
	}

	public bool IsImmediatelySpell(int userSpellId)
	{
		return false;
	}

	protected override void OnDestroy()
	{
	}
}
