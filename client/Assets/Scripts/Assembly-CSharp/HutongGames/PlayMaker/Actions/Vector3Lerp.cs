namespace HutongGames.PlayMaker.Actions
{
	public class Vector3Lerp : FsmStateAction
	{
		[RequiredField]
		public FsmVector3 fromVector;

		[RequiredField]
		public FsmVector3 toVector;

		[RequiredField]
		public FsmFloat amount;

		[RequiredField]
		public FsmVector3 storeResult;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoVector3Lerp()
		{
		}
	}
}
