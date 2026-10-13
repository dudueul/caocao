// [RE] Rebuild-only battle executor: dispatches BattleControls to unit
// actions. The original dispatched per ControlType into animation + combat
// systems (BattlePlay.BattlePlayManager$$ApplyControl analog); the decompiled
// pieces that ARE recovered are used verbatim:
//   - ClientConst.CombatPower coef arrays (decompiled cctors — REAL values)
//   - ClientConst hit/double-attack ratios (383 const fields, metadata-preserved)
//   - TroopStat, UnitData, BattleControl (rebuilt data path)
// Damage formula body is [RE-APPROX] until the SpAction decompile milestone
// (the original spread it across SpAction* classes + CommonTriggers).
using System;
using System.Collections.Generic;
using LitJson;
using UnityEngine;

namespace BattlePlay
{
	public class BattleExecutor
	{
		// [RE] 5 coef slots map to the TroopStat order: atk, def, inte, agi, mor
		// (move excluded — 5 entries). Evidence: CivilOfficer is inte-dominant
		// (135), MilitaryOfficier atk/def-dominant (100/100/25/85/85).
		public static int CalcMilitaryPower(TroopStat stat, int officerClass)
		{
			int[] coef;
			switch (officerClass)
			{
				case 0: coef = new[] { 100, 100, 75, 65, 60 }; break;   // Almighty
				case 1: coef = new[] { 100, 100, 25, 85, 85 }; break;   // MilitaryOfficier
				case 2: coef = new[] { 10, 75, 135, 50, 100 }; break;   // CivilOfficer
				default: coef = new[] { 100, 100, 100, 100, 100 }; break; // ETC
			}
			return (stat.atk * coef[0] + stat.def * coef[1] + stat.inte * coef[2]
				+ stat.agi * coef[3] + stat.mor * coef[4]) / 100;
		}

		// [RE-APPROX] base damage: atk-biased power delta vs defence, floored at
		// ClientConst.missingNormalATTDamage (=1). The exact original formula is
		// distributed across SpAction* classes — see the damage milestone.
		public static int CalcDamage(UnitData attacker, UnitData defender, bool isSkill)
		{
			if (attacker == null || defender == null)
				return 0;
			int atk = attacker.stat != null ? attacker.stat.atk : 0;
			int def = defender.stat != null ? defender.stat.def : 0;
			int raw = Math.Max(1, atk * 2 - def);   // [RE-APPROX]
			var roll = UnityEngine.Random.Range(ClientConst.minHitRatio, ClientConst.maxHitRatio + 1);
			int dmg = raw * roll / 100;
			if (dmg < ClientConst.missingNormalATTDamage)
				dmg = ClientConst.missingNormalATTDamage;
			return dmg;
		}

		// [RE] double-attack check — ClientConst.doubleAttackRate (=100 roll)
		// with doubleAttackEffectRate (=75) for the second hit's effect.
		public static bool RollDoubleAttack()
		{
			return UnityEngine.Random.Range(0, ClientConst.doubleAttackRate) == 0;
		}

		public interface IControlSink
		{
			void OnApplied(BattleControl control, JsonData result);
			void OnDamage(UnitData target, int amount, bool killed);
		}

		private readonly IControlSink sink;

		private readonly Dictionary<UnitData, int> hp = new Dictionary<UnitData, int>();

		public BattleExecutor(IControlSink sink)
		{
			this.sink = sink;
		}

		public void Register(UnitData unit, int currentHp)
		{
			if (unit != null)
				hp[unit] = currentHp;
		}

		public int GetHp(UnitData unit)
		{
			int v;
			return unit != null && hp.TryGetValue(unit, out v) ? v : 0;
		}

		// [RE] the per-type dispatch — the heart of ApplyControl.
		public void Apply(BattleControl control, UnitData controlUnit, UnitData targetUnit)
		{
			if (control == null)
				return;
			JsonData result = new JsonData();
			switch (control.type)
			{
				case ControlType.SelectUnit:
				case ControlType.SelectTile:
					result["selectedTile"] = control.tileIdx;
					control.applied = true;
					break;

				case ControlType.Attack:
				{
					int dmg = CalcDamage(controlUnit, targetUnit, false);
					bool doubleHit = RollDoubleAttack();
					if (doubleHit)
						dmg = dmg * ClientConst.doubleAttackEffectRate / 100 + dmg;   // [RE] effect 75% add-on
					ApplyDamage(targetUnit, dmg);
					result["damage"] = dmg;
					result["double"] = doubleHit;
					result["targetUnitId"] = targetUnit != null ? targetUnit.unitId : 0;
					control.applied = true;
					break;
				}

				case ControlType.SkillAttack:
				{
					int dmg = CalcDamage(controlUnit, targetUnit, true);
					ApplyDamage(targetUnit, dmg);
					result["damage"] = dmg;
					result["skillId"] = control.skillId;
					control.applied = true;
					break;
				}

				case ControlType.UseItem:
				{
					// [RE] item effect via ItemCatalog (itemEffect/effectValue)
					var item = ItemCatalog.Get(control.itemId);
					int heal = item != null ? item.effectValue : 0;
					Heal(controlUnit, heal);
					result["heal"] = heal;
					result["itemId"] = control.itemId;
					control.applied = true;
					break;
				}

				case ControlType.SkipTurn:
				case ControlType.CompleteAction:
				case ControlType.Cancel:
					control.applied = true;
					break;

				case ControlType.AIActionMove:
				case ControlType.AIActionAttack:
				case ControlType.AIActionSkill:
					// [RE] AI control arrives via multiplay socket as normal controls
					goto case ControlType.Attack;

				case ControlType.SetAttackState:
				case ControlType.SetPincerState:
				case ControlType.UserSpell:
				case ControlType.VerifyAction:
				case ControlType.UnitTag:
					control.applied = true;
					break;

				default:
					control.applied = true;
					break;
			}
			if (sink != null)
				sink.OnApplied(control, result);
		}

		private void ApplyDamage(UnitData target, int amount)
		{
			if (target == null)
				return;
			int current = GetHp(target);
			int after = Math.Max(0, current - amount);
			hp[target] = after;
			bool killed = after == 0;
			if (sink != null)
				sink.OnDamage(target, amount, killed);
		}

		private void Heal(UnitData target, int amount)
		{
			if (target == null)
				return;
			hp[target] = GetHp(target) + amount;
		}
	}
}
