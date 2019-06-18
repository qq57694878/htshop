import { VantComponent } from '../../vant-weapp/common/component';
VantComponent({
    props: {
        info: null,
        name: String,
        size: String,
        color: String,
        customStyle: String
    },
    methods: {
        onClick() {
            this.$emit('click');
        }
    }
});
