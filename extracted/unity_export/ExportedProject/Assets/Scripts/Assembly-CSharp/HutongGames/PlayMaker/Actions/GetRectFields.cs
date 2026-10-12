namespace HutongGames.PlayMaker.Actions
{
	public class GetRectFields : FsmStateAction
	{
		[RequiredField]
		public FsmRect rectVariable;

		public FsmFloat storeX;

		public FsmFloat storeY;

		public FsmFloat storeWidth;

		public FsmFloat storeHeight;

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

		private void DoGetRectFields()
		{
		}
	}
}
