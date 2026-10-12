// name: Mono.Security.Protocol.Tls.TlsServerSettings$$set_ServerKeyExchange
// entry: 02b107b4

void Mono_Security_Protocol_Tls_TlsServerSettings__set_ServerKeyExchange(long param_1,byte param_2)

{
  *(byte *)(param_1 + 0x70) = param_2 & 1;
  return;
}


