namespace HutongGames.PlayMaker.Actions
{
	public class Vector2Lerp : FsmStateAction
	{
		[RequiredField]
		public FsmVector2 fromVector;

		[RequiredField]
		public FsmVector2 toVector;

		[RequiredField]
		public FsmFloat amount;

		[RequiredField]
		public FsmVector2 storeResult;

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

		private void DoVector2Lerp()
		{
		}
	}
}
