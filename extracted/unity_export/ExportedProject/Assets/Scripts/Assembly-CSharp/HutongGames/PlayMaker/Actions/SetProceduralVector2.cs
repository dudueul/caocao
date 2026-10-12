namespace HutongGames.PlayMaker.Actions
{
	public class SetProceduralVector2 : FsmStateAction
	{
		[RequiredField]
		public FsmMaterial substanceMaterial;

		[RequiredField]
		public FsmString vector2Property;

		[RequiredField]
		public FsmVector2 vector2Value;

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

		private void DoSetProceduralVector()
		{
		}
	}
}
