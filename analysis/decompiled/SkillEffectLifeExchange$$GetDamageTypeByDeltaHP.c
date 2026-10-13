// name: SkillEffectLifeExchange$$GetDamageTypeByDeltaHP
// entry: 021806f4

undefined4 SkillEffectLifeExchange__GetDamageTypeByDeltaHP(undefined8 param_1,int param_2)

{
  undefined4 uVar1;
  
  if (0 < param_2) {
    return 5;
  }
  uVar1 = 0;
  if (param_2 == 0) {
    uVar1 = 6;
  }
  return uVar1;
}


