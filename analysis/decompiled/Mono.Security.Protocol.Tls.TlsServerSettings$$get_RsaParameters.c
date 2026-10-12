// name: Mono.Security.Protocol.Tls.TlsServerSettings$$get_RsaParameters
// entry: 02b107c8

void Mono_Security_Protocol_Tls_TlsServerSettings__get_RsaParameters
               (undefined8 *param_1,long param_2)

{
  undefined8 uVar1;
  
  uVar1 = *(undefined8 *)(param_2 + 0x50);
  param_1[7] = *(undefined8 *)(param_2 + 0x58);
  param_1[6] = uVar1;
  uVar1 = *(undefined8 *)(param_2 + 0x40);
  param_1[5] = *(undefined8 *)(param_2 + 0x48);
  param_1[4] = uVar1;
  uVar1 = *(undefined8 *)(param_2 + 0x30);
  param_1[3] = *(undefined8 *)(param_2 + 0x38);
  param_1[2] = uVar1;
  uVar1 = *(undefined8 *)(param_2 + 0x20);
  param_1[1] = *(undefined8 *)(param_2 + 0x28);
  *param_1 = uVar1;
  return;
}


