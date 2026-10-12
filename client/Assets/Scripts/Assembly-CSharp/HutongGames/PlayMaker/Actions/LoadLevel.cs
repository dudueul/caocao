using UnityEngine;

namespace HutongGames.PlayMaker.Actions
{
	public class LoadLevel : FsmStateAction
	{
		[RequiredField]
		public FsmString levelName;

		public bool additive;

		public bool async;

		public FsmEvent loadedEvent;

		public FsmBool dontDestroyOnLoad;

		public FsmEvent failedEvent;

		private AsyncOperation asyncOperation;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}
	}
}
