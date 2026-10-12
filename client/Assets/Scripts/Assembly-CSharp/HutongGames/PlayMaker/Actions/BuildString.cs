namespace HutongGames.PlayMaker.Actions
{
	public class BuildString : FsmStateAction
	{
		[RequiredField]
		public FsmString[] stringParts;

		public FsmString separator;

		public FsmBool addToEnd;

		[RequiredField]
		public FsmString storeResult;

		public bool everyFrame;

		private string result;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoBuildString()
		{
		}
	}
}
