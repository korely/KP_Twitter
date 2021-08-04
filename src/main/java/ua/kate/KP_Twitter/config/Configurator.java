package ua.kate.KP_Twitter.config;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.kate.KP_Twitter.app.AppContext;
import ua.kate.KP_Twitter.persistence.dao.TweetDao;
import ua.kate.KP_Twitter.persistence.dao.UserDao;
import ua.kate.KP_Twitter.persistence.factory.DaoAbstractFactory;

public class Configurator {

    private static Logger log = LogManager.getLogger(Configurator.class);

    public static final String DAO_TYPE = "daoType";
    public static final String INIT_DB = "initDb";
    public static final String POPULATE_DB = "populateDb";

    public static void initApp(String[] args) {
        // parse params

        Configuration config = parseArgs(args);

        //init app
        // init dao type

        DaoAbstractFactory factory = new DaoAbstractFactory(config.getDaoType());
        UserDao userDao = (UserDao) factory.createUserDao();
        TweetDao tweetDao = (TweetDao) factory.createTweetDao();

        AppContext appContext = new AppContext(config, userDao, tweetDao);

        if (config.initDb()) {
            //init db
        }

        if (config.populateDb()) {
            //populate db
        }

        System.out.println(config);

    }

    private static Configuration parseArgs(String[] args) {
        Options options = new Options();
        options.addRequiredOption("dt", DAO_TYPE, true, "choose dao type for this run");
        options.addOption(INIT_DB, false, "flag for initial creation of the DB");
        options.addOption(POPULATE_DB, false, "flag for populationn the DB with fake data for a showdown");
        try {
            CommandLine cmd = new DefaultParser().parse(options, args);
            Configuration config;
            config = Configuration.builder()
                    .daoType(cmd.getOptionValue(DAO_TYPE))
                    .initDb(cmd.hasOption(INIT_DB))
                    .populateDb(cmd.hasOption(POPULATE_DB))
                    .build();
            return config;
        } catch (ParseException e) {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }
}
