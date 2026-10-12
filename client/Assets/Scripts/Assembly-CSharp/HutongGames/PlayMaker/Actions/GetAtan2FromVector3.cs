namespace HutongGames.PlayMaker.Actions
{
	public class GetAtan2FromVector3 : FsmStateAction
	{
		public enum aTan2EnumAxis
		{
			x = 0,
			y = 1,
			z = 2
		}

		[RequiredField]
		public FsmVector3 vector3;

		[RequiredField]
		public aTan2EnumAxis xAxis;

		[RequiredField]
		public aTan2EnumAxis yAxis;

		[RequiredField]
		public FsmFloat angle;

		public FsmBool RadToDeg;

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

		private void DoATan()
		{
		}
	}
}
