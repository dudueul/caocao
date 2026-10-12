namespace HutongGames.PlayMaker.Actions
{
	public class Vector2Operator : FsmStateAction
	{
		public enum Vector2Operation
		{
			DotProduct = 0,
			Distance = 1,
			Angle = 2,
			Add = 3,
			Subtract = 4,
			Multiply = 5,
			Divide = 6,
			Min = 7,
			Max = 8
		}

		[RequiredField]
		public FsmVector2 vector1;

		[RequiredField]
		public FsmVector2 vector2;

		public Vector2Operation operation;

		public FsmVector2 storeVector2Result;

		public FsmFloat storeFloatResult;

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

		private void DoVector2Operator()
		{
		}
	}
}
