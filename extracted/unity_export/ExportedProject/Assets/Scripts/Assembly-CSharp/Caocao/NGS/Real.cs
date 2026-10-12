using System;
using System.Collections.Generic;
using System.Text;
using LitJson;

namespace Caocao.NGS
{
	public class Real : INGSClient
	{
		private bool needPatch;

		private bool ngsStarted;

		private float heartbeat;

		private static StringBuilder hex;

		private bool isDebugBuild;

		private static List<byte[]> pendingMsgQ;

		private static object ms_lock;

		private List<byte[]> msgQ;

		public void StandBy()
		{
		}

		public void Install()
		{
		}

		public void Process(byte[] data, uint sessionID)
		{
		}

		public void Uninstall()
		{
		}

		private void Log(string str)
		{
		}

		private void HealthyCheck()
		{
		}

		private static void NgsSendCallback(IntPtr bytes, uint size, uint sessionID)
		{
		}

		public void SendMessage()
		{
		}

		private void PollMessages()
		{
		}

		private void OnNGSMessages(JsonData response)
		{
		}

		private void OnCommandCheck(JsonData response)
		{
		}

		private void RunCommand(string command)
		{
		}

		private string ToHexString(byte[] ba)
		{
			return null;
		}
	}
}
