// name: System.Security.Cryptography.DecryptedInfo$$GetSalt
// entry: 01b144a4

void System_Security_Cryptography_DecryptedInfo__GetSalt(long param_1,long *param_2)

{
  undefined8 uVar1;
  
  if (param_2 == (long *)0x0) {
    func_0x010eec64(0);
  }
  uVar1 = (**(code **)(*param_2 + 0x2d0))(param_2,*(undefined8 *)(*param_2 + 0x2d8));
  *(undefined8 *)(param_1 + 0x20) = uVar1;
  if (param_2 == (long *)0x0) {
    func_0x010eec64(0);
  }
                    /* WARNING: Could not recover jumptable at 0x01b14504. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (**(code **)(*param_2 + 0x210))(param_2,0x10,*(undefined8 *)(*param_2 + 0x218));
  return;
}


